package com.hexaware.myExceptions;

public class ValidationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
        super(message);  // Pass the error message to the parent Exception class
    }
}
