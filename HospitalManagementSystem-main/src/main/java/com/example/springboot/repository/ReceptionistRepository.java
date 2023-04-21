package com.example.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist,Long>{

	Optional <Receptionist> findByReceptionistEmailIdAndReceptionistPassword(String receptinistEmailId,String receptionistPassword);
	
	public List<Receptionist> findAll();

}
