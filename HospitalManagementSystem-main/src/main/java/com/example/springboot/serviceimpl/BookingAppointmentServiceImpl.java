package com.example.springboot.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.exception.AppointmentNotValidException;
import com.example.springboot.exception.ReceptionistNotFoundException;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.BookingAppointment;
import com.example.springboot.model.Doctor;
import com.example.springboot.model.Patient;
import com.example.springboot.repository.BookingAppointmentRepository;
import com.example.springboot.repository.PatientRepository;
import com.example.springboot.service.BookingAppointmentService;
import com.example.springboot.service.DoctorService;
import com.example.springboot.service.PatientService;

@Service
public class BookingAppointmentServiceImpl implements BookingAppointmentService {
 
	@Autowired
	public BookingAppointmentRepository bookingAppointmentRepository;
	
	@Autowired
	public PatientRepository patientRepository;
	
	@Autowired
	public DoctorService doctorService;
	
	@Autowired
	public PatientService patientService;
	
	@Override
	public BookingAppointment addAppointment(long doctorId,long patientId,BookingAppointment bookingAppointment) throws AppointmentNotValidException {
	
		List<BookingAppointment> appointments = bookingAppointmentRepository.findBookingAppointmentByPatientId(patientId);
		
		for(BookingAppointment appointment:appointments) {
			Doctor doctor = appointment.getDoctor();
			if(appointment.getAppointmentDate().equals(bookingAppointment.getAppointmentDate())) {
				if(appointment.getAppointmentTime()==bookingAppointment.getAppointmentTime()) {
					if(appointment.getMeridiem().equals(bookingAppointment.getMeridiem())) {
						if(doctor.getDoctorId()==doctorId) {
						throw new AppointmentNotValidException("you cant take appointment with this doctor same time");
				
						}
					}
				}
			}
		}
		
		Doctor doctor = doctorService.getDoctorById(doctorId) ;
		
		Patient patient = patientService.getPatientById(patientId);
		
		patient.setDoctorId(doctor.getDoctorId());
		
		patientRepository.save(patient);
		
		bookingAppointment.setPatientId(patient.getPatientId());
		 
		
		
		bookingAppointment.setDoctor(doctor);
//		System.out.println("doctor"+doctor);
//		System.out.println("bookingappointment"+bookingAppointment);
		
		return bookingAppointmentRepository.save(bookingAppointment);
	
}
	@Override
	public List<BookingAppointment> getAllAppointments() {

		return bookingAppointmentRepository.findAll();
	}
	@Override
	public BookingAppointment getAppointmentById(long appointmentId) {
		 
		return bookingAppointmentRepository.findById(appointmentId).orElseThrow(()->new ResourceNotFoundException("Appointment","Appointment Id",appointmentId));
		
	}
	 
 
}

