package com.Exception;

public class InvalidStudentDataException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	

	public String getMessage() {
		return message;
	}


	public InvalidStudentDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
