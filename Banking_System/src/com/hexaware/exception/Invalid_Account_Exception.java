package com.hexaware.exception;

public class Invalid_Account_Exception extends Exception {
    private static final long serialVersionUID = 1L;

    public Invalid_Account_Exception(String message) {
        super(message);
    }
}