package com.hexaware.myExceptions;

@SuppressWarnings("serial")
public class LeaseNotFoundException extends RuntimeException {

    public LeaseNotFoundException(String message) {
        super(message);
    }
}
