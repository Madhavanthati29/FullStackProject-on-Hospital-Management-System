package com.example.springboot.validator;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.springboot.exception.AppointmentNotValidException;
import com.example.springboot.exception.IdMismatchException;
import com.example.springboot.exception.PaymentNotValidException;
import com.example.springboot.exception.PrescriptionNotValidException;
import com.example.springboot.exception.ReceptionistNotFoundException;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.exception.RoomDuplicateException;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus status,WebRequest request){
	
		Map<String,String> errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
			
		});
		return new ResponseEntity<Object> (errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ResourceNotFoundException.class) 
	public ResponseEntity<String>
	  handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
	  return new
	  ResponseEntity<String>(resourceNotFoundException.getMessage(),HttpStatus.BAD_REQUEST); }
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleConstraintViolation(SQLIntegrityConstraintViolationException ex ,WebRequest request){
		return  new
	  ResponseEntity<>("OOPS!!! duplicate entry of email got detected",HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ReceptionistNotFoundException.class) 
	public ResponseEntity<String>
	  handleReceptionistNotFoundException(ReceptionistNotFoundException ex) {
	  return new
	  ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST); }
	
	@ExceptionHandler(AppointmentNotValidException.class) 
	public ResponseEntity<String>
	  handleAppointmentNotValidException(AppointmentNotValidException ex) {
	  return new
	  ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST); }
	
	@ExceptionHandler(IdMismatchException.class) 
	public ResponseEntity<String>
	  handleIdMismatchException(IdMismatchException ex) {
	  return new
	  ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST); }

	@ExceptionHandler(PrescriptionNotValidException .class) 
	public ResponseEntity<String>
	  handlePrescriptionNotValidException (PrescriptionNotValidException  ex) {
	  return new
	  ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST); }

	@ExceptionHandler(RoomDuplicateException.class) 
	public ResponseEntity<String>
	  handleRoomDuplicateException(RoomDuplicateException ex) {
	  return new
	  ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST); }
	@ExceptionHandler(PaymentNotValidException.class)
	public ResponseEntity<String>
	  handlePaymentNotValidException(PaymentNotValidException ex) {
	  return new
	  ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST); }
	
}