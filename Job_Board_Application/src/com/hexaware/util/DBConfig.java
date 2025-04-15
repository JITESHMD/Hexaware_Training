package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/job_board_db?useSSL=false";  // Your database URL
    private static final String DB_USERNAME = "root";  // Your database username
    private static final String DB_PASSWORD = "Jithu@10";  // Your database password

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to the database", e);
        }
    }
}
