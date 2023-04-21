package com.example.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.model.Patient;
import com.example.springboot.model.Payment;
import com.example.springboot.model.Prescription;
import com.example.springboot.service.PatientService;
import com.example.springboot.service.PaymentService;
import com.example.springboot.service.PrescriptionService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//http://localhost:8088/api/patient
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	public PatientService patientService;
	
	@Autowired
	public PrescriptionService prescriptionService;
	
	@Autowired
	public PaymentService paymentService;
	
	//Registration
	@PostMapping("/register")
	public ResponseEntity<Patient> saveAdmin(@Valid @RequestBody Patient patient){
		System.out.println("Patient Registration Succesfull "+patient);
		return new ResponseEntity<Patient>(patientService.savePatient(patient),HttpStatus.CREATED);
	}
	
	//login
	@PostMapping("/login")
	public ResponseEntity<Patient> loginPatient(@RequestBody Patient patient){
		return new ResponseEntity<Patient>(patientService.loginPatient(patient),HttpStatus.OK);
	}
	
	@PutMapping("{patientId}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("patientId") long patientId, @RequestBody Patient patient) {
	
		return new ResponseEntity<Patient>(patientService.updatePatient(patient,patientId),HttpStatus.OK);
	}
	
	@GetMapping("{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("patientId") long patientId){
		
		return new ResponseEntity<Patient>(patientService.getPatientById(patientId),HttpStatus.OK);
	}
	
	@GetMapping("/prescription")
	public List<Prescription> getAllPrescriptions(){
		return prescriptionService.getAllPrescriptions();
	}
	
	@GetMapping("/prescription/{precriptionId}")
	public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("prescriptionId") long prescriptionId){
		
		return new ResponseEntity<Prescription>(prescriptionService.getPrescriptionById(prescriptionId),HttpStatus.OK);
		
	}
	
	@GetMapping
	public List<Payment> getAllPayments(){
		return paymentService.getAllPayments();
	}
}
