package com.hexaware.exception;

@SuppressWarnings("serial")
public class FileUploadException extends Exception {

    // Constructor for the exception with a custom message
    public FileUploadException(String message) {
        super(message);
    }

    // Constructor for the exception with both a custom message and a cause
    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
