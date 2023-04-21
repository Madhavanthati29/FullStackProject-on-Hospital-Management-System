package com.example.springboot.service;

import java.util.List;

import com.example.springboot.exception.ReceptionistNotFoundException;
import com.example.springboot.model.Patient;
import com.example.springboot.model.Receptionist;

public interface ReceptionistService {

	Receptionist saveReceptionist(Receptionist receptionist);

	Receptionist loginReceptionist(Receptionist receptionist);

	Patient getPatientById(long patientId) throws ReceptionistNotFoundException;

	List<Patient> getAllPatients() throws ReceptionistNotFoundException;

	Receptionist updateReceptionist(Receptionist receptionist, long receptionistId);

	void deletePatient(long patientId) throws ReceptionistNotFoundException;

	List<Receptionist> getAllReceptionists();

	Receptionist getReceptionistById(long receptionistId);

	void deleteReceptionist(long receptionistId);

	Patient updatePatientAfterCouncelling(long patientId, long prescriptionId,long roomId) throws Exception;
	

}
