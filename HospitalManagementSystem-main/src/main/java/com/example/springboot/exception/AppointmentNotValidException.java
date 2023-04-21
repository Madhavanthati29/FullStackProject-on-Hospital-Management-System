package com.example.springboot.exception;

public class AppointmentNotValidException extends Exception{

	public AppointmentNotValidException (String msg) {
		super(msg);
	}
}
