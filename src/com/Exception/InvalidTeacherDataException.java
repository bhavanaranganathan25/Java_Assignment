package com.Exception;

public class InvalidTeacherDataException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidTeacherDataException(String message) {
		super();
		this.message = message;
	}



	public String getMessage() {
		return message;
	}
	
	

}
