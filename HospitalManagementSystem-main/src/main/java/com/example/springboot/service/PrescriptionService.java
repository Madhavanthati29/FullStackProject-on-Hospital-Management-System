package com.example.springboot.service;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.example.springboot.exception.IdMismatchException;
import com.example.springboot.exception.PrescriptionNotValidException;
import com.example.springboot.model.Prescription;


public interface PrescriptionService {
	
	 
	
	List<Prescription> getAllPrescriptions();
	
	//Prescription getPrescriptionById(long prescriptionId,long patientId);
	
	void deletePrescription(long prescriptionId);
	
	Prescription getPrescriptionById(long prescriptionId);


	Prescription generatePrescription(@Valid Prescription prescription, long appointmentId) throws ParseException, IdMismatchException, PrescriptionNotValidException;


	
	 

}
