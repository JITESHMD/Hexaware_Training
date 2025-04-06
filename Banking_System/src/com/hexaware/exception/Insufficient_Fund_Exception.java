package com.hexaware.exception;

public class Insufficient_Fund_Exception extends Exception {
    private static final long serialVersionUID = 1L;

    public Insufficient_Fund_Exception(String message) {
        super(message);
    }
}