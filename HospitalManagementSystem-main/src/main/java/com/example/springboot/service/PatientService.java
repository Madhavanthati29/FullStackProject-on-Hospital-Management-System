package com.example.springboot.service;

import com.example.springboot.model.Patient;

public interface PatientService {

	Patient savePatient(Patient patient);

	Patient loginPatient(Patient patient);

	Patient updatePatient(Patient patient, long patientId);

	Patient getPatientById(long patientId);
	
	

}
