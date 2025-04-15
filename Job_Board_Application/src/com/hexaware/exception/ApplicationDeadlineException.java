package com.hexaware.exception;



@SuppressWarnings("serial")
public class ApplicationDeadlineException extends Exception {

    // Constructor for the exception with a custom message
    public ApplicationDeadlineException(String message) {
        super(message);
    }

    // Constructor for the exception with both a custom message and a cause
    public ApplicationDeadlineException(String message, Throwable cause) {
        super(message, cause);
    }
}
