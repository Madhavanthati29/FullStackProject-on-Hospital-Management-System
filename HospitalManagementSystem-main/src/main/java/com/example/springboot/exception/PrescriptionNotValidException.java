package com.example.springboot.exception;

public class PrescriptionNotValidException extends Exception {

	public PrescriptionNotValidException(String msg) {
		super(msg);
	}
}
