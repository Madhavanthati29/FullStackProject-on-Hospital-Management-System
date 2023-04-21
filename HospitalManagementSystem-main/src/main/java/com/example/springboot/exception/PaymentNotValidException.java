package com.example.springboot.exception;

public class PaymentNotValidException extends Exception {

	public PaymentNotValidException(String msg) {
		super(msg);
	}
}
