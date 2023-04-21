package com.example.springboot.serviceimpl;

import java.text.ParseException;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.exception.IdMismatchException;
import com.example.springboot.exception.PrescriptionNotValidException;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.BookingAppointment;
import com.example.springboot.model.Doctor;
import com.example.springboot.model.Patient;
import com.example.springboot.model.Prescription;
 
import com.example.springboot.repository.PatientRepository;
import com.example.springboot.repository.PrescriptionRepository;
import com.example.springboot.service.BookingAppointmentService;
import com.example.springboot.service.DoctorService;
import com.example.springboot.service.PatientService;
import com.example.springboot.service.PrescriptionService;

 

@Service
public class PrescriptionserviceImpl implements PrescriptionService  {
	
	@Autowired
	public PrescriptionRepository prescriptionRepository;
	
	@Autowired
	public PatientRepository patientRepository; 
	
	@Autowired
	public BookingAppointmentService bookingAppointmentService;
	
	@Autowired
	public PatientService patientService;
	
	@Autowired
	public DoctorService doctorService;
	
	
	
	@Override
	public Prescription generatePrescription (Prescription prescription,long appointmentId) throws ParseException, IdMismatchException, PrescriptionNotValidException {
		
		BookingAppointment bookingAppointment = bookingAppointmentService.getAppointmentById(appointmentId);
		List<Prescription> prescriptions = prescriptionRepository.findAll();
		
		for(Prescription prescriptionsDetail : prescriptions) {
			BookingAppointment appointment = prescriptionsDetail.getBookingAppointment();
			if(prescriptionsDetail.getDoctorId()==prescription.getDoctorId()) {
				if(prescriptionsDetail.getPatientId()==prescription.getPatientId()) {
					if(appointment.getAppointmentId()==appointmentId) {
						throw new PrescriptionNotValidException ("Prescription is not valid :(");
					}
				}
			}
		}
		
		
		Doctor oldDoctor =  bookingAppointment.getDoctor();
 
		Doctor doctor = doctorService.getDoctorById(prescription.doctorId);
		Patient patient = patientService.getPatientById(prescription.patientId);
		
		if(doctor.getDoctorId()!= oldDoctor.getDoctorId()) {
			throw new IdMismatchException("Doctor id is not matching please check that one ");
		}else if(patient.getPatientId()!=bookingAppointment.getPatientId()) {
			throw new IdMismatchException("Patient id is not matching please check that one ");
		}
		
		prescription.setBookingAppointment(bookingAppointment);
	
		return prescriptionRepository.save(prescription);
	
}

	@Override
	public List<Prescription> getAllPrescriptions() {
		 
		return prescriptionRepository.findAll();
	}

	 
	@Override
	public void deletePrescription(long prescriptionId) {
	 
		
	}

	@Override
	public Prescription getPrescriptionById(long prescriptionId) {
		 
		return prescriptionRepository.findById(prescriptionId).orElseThrow(()->new ResourceNotFoundException("prescription","PrescriptionId",prescriptionId));
	}


}
