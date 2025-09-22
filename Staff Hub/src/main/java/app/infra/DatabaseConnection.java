// 1) DatabaseConnection
package infra;

import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final Connection connection;

    private DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/staffhub", "root", "PUC@1234");
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    public static DatabaseConnection getInstance() {
        if (instance == null) instance = new DatabaseConnection();
        return instance;
    }
    public Connection getConnection() { return connection; }
}
