package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Prescription;


public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
	Optional<Prescription> findByPrescriptionIdAndPatientId(long prescriptionId,long patientId);

}