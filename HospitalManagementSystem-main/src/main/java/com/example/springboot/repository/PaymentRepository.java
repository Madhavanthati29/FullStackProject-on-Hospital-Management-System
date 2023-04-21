package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

}
