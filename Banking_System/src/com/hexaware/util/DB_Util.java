package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Util {
    private static final String URL = "jdbc:mysql://localhost:3306/hmbank?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";              
    private static final String PASSWORD = "Jithu@10";      

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
