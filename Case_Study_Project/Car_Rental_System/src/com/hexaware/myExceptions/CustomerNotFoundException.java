package com.hexaware.myExceptions;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
