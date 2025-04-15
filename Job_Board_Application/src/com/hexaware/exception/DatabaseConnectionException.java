package com.hexaware.exception;

@SuppressWarnings("serial")
public class DatabaseConnectionException extends Exception {

    // Constructor for the exception with a custom message
    public DatabaseConnectionException(String message) {
        super(message);
    }

    // Constructor for the exception with both a custom message and a cause
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

