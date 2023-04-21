package com.example.springboot.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.exception.IdMismatchException;
import com.example.springboot.exception.PrescriptionNotValidException;
import com.example.springboot.model.BookingAppointment;
import com.example.springboot.model.Doctor;
import com.example.springboot.model.Patient;
import com.example.springboot.model.Prescription;
import com.example.springboot.service.BookingAppointmentService;
import com.example.springboot.service.DoctorService;
import com.example.springboot.service.PrescriptionService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//http://localhost:8088/api/doctor
@RequestMapping("/api/doctor")
public class DoctorController {

	@Autowired
	public DoctorService doctorService;
	
	@Autowired
	public PrescriptionService prescriptionService;
	
	@Autowired
	public BookingAppointmentService bookingAppointmentService;
	//registration
	@PostMapping("/register")
	public ResponseEntity<Doctor> saveAdmin(@Valid @RequestBody Doctor doctor){
		System.out.println("Doctor Registration Succesfull "+doctor);
		return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor),HttpStatus.CREATED);
	}
	//log in
	@PostMapping("/login")
	public ResponseEntity<Doctor> loginDoctor(@RequestBody Doctor doctor){
		return new ResponseEntity<Doctor>(doctorService.loginDoctor(doctor),HttpStatus.OK);
	}
	
	@PutMapping("{doctorId}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor) {
	
		return new ResponseEntity<Doctor>(doctorService.updateDoctor(doctor,doctorId),HttpStatus.OK);
	}
	
	@GetMapping("/booking")
	public List<BookingAppointment> getAllAppointments(){
		
		return bookingAppointmentService.getAllAppointments() ;
	}
	
	@PostMapping("/register/prescription/{appointmentId}")
	public ResponseEntity<Prescription> generatePrescription(@Valid @RequestBody Prescription prescription,@PathVariable("appointmentId") long appointmentId) throws ParseException, IdMismatchException, PrescriptionNotValidException{
		 	return new ResponseEntity<Prescription>(prescriptionService.generatePrescription(prescription,appointmentId),HttpStatus.CREATED);
	}
	
	@GetMapping("patient/{doctorId}")
	public List<Patient> getAllPatientsByDoctorId(@PathVariable("doctorId") long doctorId){
		
		return doctorService.getAllPatientsByDoctorId(doctorId);
	}
	
	@GetMapping("/doctor")
	public List<Doctor> getAllDoctors(){
		return doctorService.getAllDoctors();
	}
	
	// delete hotel
	@DeleteMapping("{doctorId}")
	public ResponseEntity<Boolean> deleteDoctorById(@PathVariable long doctorId) {
		doctorService.deleteDoctor(doctorId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
	
	@GetMapping("{doctorId}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") long doctorId){
		
		return new ResponseEntity<Doctor>(doctorService.getDoctorById(doctorId),HttpStatus.OK);
	}
	
	
}
