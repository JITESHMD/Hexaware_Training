package com.hexaware.myExceptions;


public class InvalidPhoneNumberException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
