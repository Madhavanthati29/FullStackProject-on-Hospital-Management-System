package com.example.springboot.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
import com.example.springboot.service.PrescriptionService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//http://localhost:8088/api/Prescription
@RequestMapping("/api/Prescription")
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;
	
//	@PostMapping("/register")
//	public ResponseEntity<Prescription> savePrescription(@Valid @RequestBody Prescription prescription){
//		System.out.println(" Registration Succesfull "+prescription);
//		return new ResponseEntity<Prescription>(prescriptionService.savePrescription(prescription),HttpStatus.CREATED);
//	}
	//delete by id
		@DeleteMapping("{PriscriptionId}")
		public ResponseEntity<Boolean> deletePrescription(@PathVariable("prescriptionId") long prescriptionId){
			prescriptionService.deletePrescription(prescriptionId);
			boolean flag = true;
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
			
			

		}
}
