package com.example.springboot.service;

import java.util.List;

import com.example.springboot.exception.IdMismatchException;
import com.example.springboot.model.Payment;

public interface PaymentService {

	

	Payment addPayment(Payment payment,long patientId,long prescriptionId) throws IdMismatchException, Exception;

	List<Payment> getAllPayments();

	Payment getPaymentById(long paymentId);
}
