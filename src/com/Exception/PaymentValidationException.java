package com.Exception;

public class PaymentValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	public PaymentValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	
}
