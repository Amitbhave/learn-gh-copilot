import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HistoryRecordTest {

    @Test
    void testConstructorAndGetters() {
        HistoryRecord record = new HistoryRecord(1, 10.0, '+', 5.0, 15.0, "2024-01-15 10:30:00");

        assertEquals(1, record.getId());
        assertEquals(10.0, record.getNum1());
        assertEquals('+', record.getOperator());
        assertEquals(5.0, record.getNum2());
        assertEquals(15.0, record.getResult());
        assertEquals("2024-01-15 10:30:00", record.getTimestamp());
    }

    @Test
    void testToStringFormat() {
        HistoryRecord record = new HistoryRecord(1, 10.0, '+', 5.0, 15.0, "2024-01-15 10:30:00");
        String expected = "[2024-01-15 10:30:00] 10.00 + 5.00 = 15.00";
        assertEquals(expected, record.toString());
    }
}
