package com.example.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.exception.AppointmentNotValidException;
import com.example.springboot.exception.ReceptionistNotFoundException;
import com.example.springboot.model.BookingAppointment;

import com.example.springboot.service.BookingAppointmentService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//http://localhost:8088/api/booking
@RequestMapping("/api/booking")
public class BookingAppointmentController {
	
	@Autowired
	private BookingAppointmentService bookingAppointmentService;
	
	@PostMapping("/register/{doctorId}/{patientId}")
	public ResponseEntity<BookingAppointment> addAppointment(@PathVariable("doctorId") long doctorId,@PathVariable("patientId") long patientId, @Valid @RequestBody BookingAppointment bookingAppointment) throws AppointmentNotValidException{
		System.out.println("Booking appointment Succesfull "+bookingAppointment);
		return new ResponseEntity<BookingAppointment>(bookingAppointmentService.addAppointment(doctorId,patientId,bookingAppointment),HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<BookingAppointment> getAllAppointments(){
		
		return bookingAppointmentService.getAllAppointments();
	}
	
	@GetMapping("{appointmentId}")
	public ResponseEntity<BookingAppointment> getAppointmentById(@PathVariable("appointmentId") long appointmentId){
		
		return new ResponseEntity<BookingAppointment>(bookingAppointmentService.getAppointmentById(appointmentId),HttpStatus.OK);
	}
	
}

