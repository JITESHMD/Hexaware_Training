package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    
    // Method to load the connection string from a properties file
    public static String getConnectionString(String fileName) {
        Properties properties = new Properties();
        try (FileInputStream fileInput = new FileInputStream(fileName)) {
            properties.load(fileInput);
            return properties.getProperty("connectionString");
        } catch (IOException e) {
            System.out.println("Error reading properties file: " + e.getMessage());
            return null;
        }
    }
}
