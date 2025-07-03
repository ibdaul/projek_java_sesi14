
package main.java.com.penggajian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_penggajian"; // Ganti dengan nama database Anda
    private static final String USER = "root"; // Ganti dengan username database Anda
    private static final String PASSWORD = ""; // Ganti dengan password database Anda

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}