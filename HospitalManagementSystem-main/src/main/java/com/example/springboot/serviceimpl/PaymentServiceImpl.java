package com.example.springboot.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.springboot.exception.IdMismatchException;
import com.example.springboot.exception.PaymentNotValidException;
import com.example.springboot.exception.ResourceNotFoundException;

import com.example.springboot.model.Patient;
import com.example.springboot.model.Payment;
import com.example.springboot.model.Prescription;
import com.example.springboot.model.Room;
import com.example.springboot.repository.PatientRepository;
import com.example.springboot.repository.PaymentRepository;
import com.example.springboot.repository.PrescriptionRepository;
import com.example.springboot.service.PatientService;
import com.example.springboot.service.PaymentService;
import com.example.springboot.service.PrescriptionService;
import com.example.springboot.service.RoomService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientService patientService;

//	@Autowired
//	private RoomRepository roomRepository;

	@Autowired
	private RoomService roomService;

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private PrescriptionService prescriptionService;


	@Override
	public Payment addPayment(Payment payment, long patientId, long prescriptionId) throws Exception {
		Prescription gettingPrescription = prescriptionService.getPrescriptionById(prescriptionId);
		Patient patient = patientService.getPatientById(patientId);
		String status = gettingPrescription.getStatus();
		if (status.equals("admit")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new Date();
			payment.setPaymentDate(date);
			payment.setPrescriptionId(gettingPrescription.getPrescriptionId());
			payment.setAdmitDate(date);
			payment.setRoomId(1);
			payment.setPatient(patient);
			patient.setDischargeDate(date);
			patient.setPaymentStatus(payment.getPaymentStatus());
			Prescription pr = gettingPrescription;
			pr.setStatus("paid");
			prescriptionRepository.save(pr);
			patientRepository.save(patient);
		} else {
			if (gettingPrescription.getStatus().equals("paid")) {
				throw new PaymentNotValidException("Payment already has done by you ");
			}
		}
		return paymentRepository.save(payment);
	}
	@Override
	public List<Payment> getAllPayments() {
		 
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(long paymentId) {
	 
		return paymentRepository.findById(paymentId).orElseThrow(()-> new ResourceNotFoundException("PaymentDetails","PaymentId",paymentId));
	}

}
