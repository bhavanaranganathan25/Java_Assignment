package com.Exception;

public class DuplicateEnrollmentException extends Exception{

	private static final long serialVersionUID = 1L;
	 private String message;
	public String getMessage() {
		return message;
	}
	public DuplicateEnrollmentException(String message) {
		super();
		this.message = message;
	}
	
}
