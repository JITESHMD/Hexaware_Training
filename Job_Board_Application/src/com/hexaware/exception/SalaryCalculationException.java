package com.hexaware.exception;

@SuppressWarnings("serial")
public class SalaryCalculationException extends Exception {

    // Constructor for the exception with a custom message
    public SalaryCalculationException(String message) {
        super(message);
    }

    // Constructor for the exception with both a custom message and a cause
    public SalaryCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
