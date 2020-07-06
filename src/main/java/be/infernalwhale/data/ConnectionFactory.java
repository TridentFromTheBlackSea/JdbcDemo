package be.infernalwhale.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://moktok.javawebdev.com:33306/moktok",
                "moktok",
                "nd8sd75d"
        );
    }
}
