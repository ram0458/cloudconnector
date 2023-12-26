package com.cloud.connector.exception;

public class ValidationException  extends Exception{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String errorMsg) {
		super(errorMsg);
	}

}
