package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
	
	Optional<Doctor> findByDoctorEmailIdAndDoctorPassword(String emailId,String password);
	
}
