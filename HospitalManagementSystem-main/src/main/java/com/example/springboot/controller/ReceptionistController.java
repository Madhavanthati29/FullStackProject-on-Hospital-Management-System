package com.example.springboot.controller;

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

import com.example.springboot.exception.ReceptionistNotFoundException;
import com.example.springboot.model.Patient;
import com.example.springboot.model.Prescription;
import com.example.springboot.model.Receptionist;
import com.example.springboot.repository.ReceptionistRepository;
import com.example.springboot.service.PatientService;
import com.example.springboot.service.PrescriptionService;
import com.example.springboot.service.ReceptionistService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//http://localhost:8088/api/receptionist
@RequestMapping("/api/receptionist")
public class ReceptionistController {
	
	@Autowired
	public ReceptionistService receptionistService;
	
	@Autowired
	public PatientService patientService;
	
	@Autowired
	public PrescriptionService prescriptionService;
	
	@Autowired
	public ReceptionistRepository receptionistRepository;
	
	//registration
	@PostMapping("/register")
	public ResponseEntity<Receptionist> saveReceptionist(@Valid @RequestBody Receptionist receptionist){
		System.out.println("Receptionist Registration Succesfull "+receptionist);
		return new ResponseEntity<Receptionist>(receptionistService.saveReceptionist(receptionist),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Receptionist> loginRecptionist(@RequestBody Receptionist receptionist){
		return new ResponseEntity<Receptionist>(receptionistService.loginReceptionist(receptionist),HttpStatus.OK);
	}
 
	//get all patient details by id
	@GetMapping("patient/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("patientId") long patientId){
		
		return new ResponseEntity<Patient>(patientService.getPatientById(patientId),HttpStatus.OK);
	}
	//get all patients
	@GetMapping("/patient")
	public List<Patient> getAllPatients() throws ReceptionistNotFoundException{
		return receptionistService.getAllPatients();
	}
	
	//delete patient
	@DeleteMapping("patient/{patientId}")
	public ResponseEntity<Boolean> deletePatient(@PathVariable("patientId") long patientId) throws ReceptionistNotFoundException{
		receptionistService.deletePatient(patientId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		
		
	}
	
	//updating patient after knowing admit and discharge date details
	//this we can come to know once after knowing all prescription details
	@PutMapping("patient/{patientId}/{prescriptionId}/{roomId}")
	public ResponseEntity<Patient> updatePatientAfterCouncelling(@PathVariable("patientId") long patientId,@PathVariable("prescriptionId") long prescriptionId,@PathVariable("roomId") long roomId) throws Exception {
	
		
		return new ResponseEntity<Patient>(receptionistService.updatePatientAfterCouncelling(patientId,prescriptionId,roomId),HttpStatus.OK);
	}
	
	    
		@PutMapping("{receptionistId}") 
		public ResponseEntity<Receptionist> updateReceptionist(@PathVariable("receptionistId") long receptionistId, @RequestBody Receptionist receptionist) {
		
			return new ResponseEntity<Receptionist>(receptionistService.updateReceptionist(receptionist,receptionistId),HttpStatus.OK);
		}
		
		//get all prescription details
		@GetMapping("/patient/prescription")
		public List<Prescription> getAllPrescriptions(){
			return prescriptionService.getAllPrescriptions();
		}
		
		@GetMapping("/patient/prescription/{precriptionId}")
		public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("prescriptionId") long prescriptionId){
			return new ResponseEntity<Prescription>(prescriptionService.getPrescriptionById(prescriptionId),HttpStatus.OK);
			
		}
		
		
}
