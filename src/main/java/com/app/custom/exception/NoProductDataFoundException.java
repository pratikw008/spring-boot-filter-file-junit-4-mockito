package com.app.custom.exception;

public class NoProductDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoProductDataFoundException() {
	}

	public NoProductDataFoundException(String message) {
		super(message);
	}
}
