package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Doctor;
import com.example.springboot.model.Patient;

public interface DoctorService {

	Doctor saveDoctor(Doctor doctor);

	Doctor loginDoctor(Doctor doctor);

	List<Doctor> getAllDoctors();

	Doctor getDoctorById(long doctorId);

	void deleteDoctor(long doctorId);

	Doctor updateDoctor(Doctor doctor, long doctorId);

	List<Patient> getAllPatientsByDoctorId(long doctorId);
	
	
	

}
