package com.bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected");
                createTables();
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return connection;
    }

    private static void createTables() {
        String customerTable =
                "CREATE TABLE IF NOT EXISTS customers (" +
                        "  id INT PRIMARY KEY AUTO_INCREMENT," +
                        "  first_name VARCHAR(50)," +
                        "  surname VARCHAR(50)," +
                        "  address VARCHAR(200))";

        String accountTable =
                "CREATE TABLE IF NOT EXISTS accounts (" +
                        "  account_number VARCHAR(20) PRIMARY KEY," +
                        "  customer_id INT," +
                        "  account_type VARCHAR(20)," +
                        "  balance DECIMAL(15,2)," +
                        "  branch VARCHAR(100)," +
                        "  employer_name VARCHAR(100)," +
                        "  employer_address VARCHAR(200)," +
                        "  FOREIGN KEY (customer_id) REFERENCES customers(id))";

        try (Connection conn = getConnection()) {
            conn.createStatement().execute(customerTable);
            conn.createStatement().execute(accountTable);
            System.out.println("✅ Tables created");
        } catch (SQLException e) {
            System.err.println("Table error: " + e.getMessage());
        }
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}