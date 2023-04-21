package com.example.springboot.serviceimpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Doctor;
import com.example.springboot.model.Patient;
import com.example.springboot.repository.DoctorRepository;
import com.example.springboot.repository.PatientRepository;
import com.example.springboot.service.DoctorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	public DoctorRepository doctorRepository;
	
	@Autowired
	public PatientRepository patientRepository;

	@Override
	public Doctor saveDoctor(Doctor doctor) {
		System.out.println("Doctor Registration Succesfull "+doctor);
		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor loginDoctor(Doctor doctor) {
		return this.doctorRepository.findByDoctorEmailIdAndDoctorPassword(doctor.doctorEmailId, doctor.doctorPassword).orElseThrow(()->new ResourceNotFoundException("Doctor ", "EmaildId",doctor.doctorEmailId+"and password "+doctor.doctorPassword));
	}

	@Override
	public List<Doctor> getAllDoctors() {
		
		return doctorRepository.findAll();
	}

	@Override
	public Doctor getDoctorById(long doctorId) {
		return doctorRepository.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor","DoctorId",doctorId));
	}

	@Override
	public void deleteDoctor(long doctorId) {
		doctorRepository.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor","DoctorId",doctorId));
		doctorRepository.deleteById(doctorId);
		
	}

	@Override
	public Doctor updateDoctor(Doctor doctor, long doctorId) {
		Doctor newDetails = doctorRepository.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor","DoctorId",doctorId));
		
		newDetails.setAddress(doctor.getAddress());
		newDetails.setAge(doctor.getAge());
		newDetails.setContactNumber(doctor.getContactNumber());
		newDetails.setDoctorEmailId(doctor.getDoctorEmailId());
		newDetails.setFirstName(doctor.getFirstName());
		newDetails.setGender(doctor.getGender());
		newDetails.setLastName(doctor.getLastName());
		newDetails.setSpecialization(doctor.getSpecialization());
		
		doctorRepository.save(newDetails);
		
		
		
		return newDetails;
	}

	@Override
	public List<Patient> getAllPatientsByDoctorId(long doctorId) {
		
		return patientRepository.findPatientByDoctorId(doctorId);
	}
	
	
	
	
	
}
