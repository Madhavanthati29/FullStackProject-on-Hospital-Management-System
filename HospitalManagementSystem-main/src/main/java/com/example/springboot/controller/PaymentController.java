package com.example.springboot.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.exception.IdMismatchException;
import com.example.springboot.model.Payment;
import com.example.springboot.service.PaymentService;
import lombok.AllArgsConstructor;

//http://localhost:8088/api/payment
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

	@Autowired 
	private PaymentService paymentService; 
	
	@PostMapping("/register/{patientId}/{prescriptionId}")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment,@PathVariable("patientId") long patientId,
		 @PathVariable("prescriptionId") long prescriptionId) throws Exception {

		return new ResponseEntity<Payment>(paymentService.addPayment(payment, patientId,prescriptionId),HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Payment> getAllPayments(){
		return paymentService.getAllPayments();
	}
	
	@GetMapping("{paymentId}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable("paymentId") long paymentId){
		
		return new ResponseEntity<Payment>(paymentService.getPaymentById(paymentId),HttpStatus.OK);
	}
	
}
