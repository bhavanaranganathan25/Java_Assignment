package com.Exception;

public class StudentNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	public StudentNotFoundException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	

}
