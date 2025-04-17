package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public static Properties getProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            props.load(fis);

            // manually construct the URL
            String hostname = props.getProperty("hostname");
            String port = props.getProperty("port");
            String dbname = props.getProperty("dbname");

            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?useSSL=false";
            props.setProperty("url", url);

        } catch (IOException e) {
            System.out.println("Error reading db.properties: " + e.getMessage());
        }
        return props;
    }
}
