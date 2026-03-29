import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryRepositoryTest {

    private Connection connection;
    private HistoryRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) throws SQLException {
        String dbName = "test_" + testInfo.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
        connection = DriverManager.getConnection("jdbc:h2:mem:" + dbName + ";DB_CLOSE_DELAY=0");
        repository = new HistoryRepository(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void testSaveAndFindAll() {
        repository.save(10, '+', 5, 15);

        List<HistoryRecord> records = repository.findAll();
        assertEquals(1, records.size());

        HistoryRecord record = records.get(0);
        assertEquals(10.0, record.getNum1());
        assertEquals('+', record.getOperator());
        assertEquals(5.0, record.getNum2());
        assertEquals(15.0, record.getResult());
        assertNotNull(record.getTimestamp());
    }

    @Test
    void testFindAllEmpty() {
        List<HistoryRecord> records = repository.findAll();
        assertTrue(records.isEmpty());
    }

    @Test
    void testMultipleSaves() {
        repository.save(1, '+', 2, 3);
        repository.save(4, '*', 5, 20);
        repository.save(10, '-', 3, 7);

        List<HistoryRecord> records = repository.findAll();
        assertEquals(3, records.size());
        // Ordered by timestamp DESC — newest first
        // All inserted at ~same time so check all are present
        assertTrue(records.stream().anyMatch(r -> r.getResult() == 3.0));
        assertTrue(records.stream().anyMatch(r -> r.getResult() == 20.0));
        assertTrue(records.stream().anyMatch(r -> r.getResult() == 7.0));
    }

    @Test
    void testSavePreservesValues() {
        repository.save(3.14, '/', 2.0, 1.57);

        List<HistoryRecord> records = repository.findAll();
        assertEquals(1, records.size());

        HistoryRecord r = records.get(0);
        assertEquals(3.14, r.getNum1(), 0.001);
        assertEquals('/', r.getOperator());
        assertEquals(2.0, r.getNum2(), 0.001);
        assertEquals(1.57, r.getResult(), 0.001);
    }
}
