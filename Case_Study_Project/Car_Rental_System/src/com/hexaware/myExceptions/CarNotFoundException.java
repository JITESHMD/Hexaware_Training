package com.hexaware.myExceptions;

@SuppressWarnings("serial")
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String message) {
        super(message);
    }
}
