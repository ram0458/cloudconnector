package com.cloud.connector.exception;

public class UnAuthorizedException extends Exception{

	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(String error) {
		super(error);
	}
}
