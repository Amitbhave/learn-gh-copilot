import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    private final Connection connection;

    public HistoryRepository(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS history ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "num1 DOUBLE, "
                    + "operator CHAR(1), "
                    + "num2 DOUBLE, "
                    + "result DOUBLE, "
                    + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize history table", e);
        }
    }

    public void save(double num1, char operator, double num2, double result) {
        String sql = "INSERT INTO history (num1, operator, num2, result) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, num1);
            ps.setString(2, String.valueOf(operator));
            ps.setDouble(3, num2);
            ps.setDouble(4, result);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save history record", e);
        }
    }

    public List<HistoryRecord> findAll() {
        List<HistoryRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM history ORDER BY timestamp DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new HistoryRecord(
                        rs.getInt("id"),
                        rs.getDouble("num1"),
                        rs.getString("operator").charAt(0),
                        rs.getDouble("num2"),
                        rs.getDouble("result"),
                        rs.getString("timestamp")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve history", e);
        }
        return records;
    }
}
