package com.hexaware.exception;

public class InvalidEmailFormatException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmailFormatException(String message) {
        super(message);
    }

    public InvalidEmailFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
