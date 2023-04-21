package com.example.springboot.serviceimpl;

 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.exception.ReceptionistNotFoundException;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.exception.RoomDuplicateException;
import com.example.springboot.model.BookingAppointment;
import com.example.springboot.model.Patient;
import com.example.springboot.model.Prescription;
import com.example.springboot.model.Receptionist;
import com.example.springboot.model.Room;
import com.example.springboot.repository.BookingAppointmentRepository;
import com.example.springboot.repository.PatientRepository;
import com.example.springboot.repository.ReceptionistRepository;
import com.example.springboot.service.AdminService;
import com.example.springboot.service.PatientService;
import com.example.springboot.service.PrescriptionService;
import com.example.springboot.service.ReceptionistService;
import com.example.springboot.service.RoomService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReceptionistServiceImpl implements ReceptionistService {

	@Autowired
	public ReceptionistRepository receptionistRepository;
	
	@Autowired
	public PatientRepository patientRepository;
	
	@Autowired
	public BookingAppointmentRepository bookingAppointmentRepository;
	
	@Autowired
	public PrescriptionService prescriptionService;
	
	@Autowired
	public RoomService roomService; 
	
	@Override
	public Receptionist saveReceptionist(Receptionist receptionist) {
		System.out.println("Receptionist Registration Succesfull "+receptionist);
		return receptionistRepository.save(receptionist);
	}

	@Override
	public Receptionist loginReceptionist(Receptionist receptionist) {
		 
		return this.receptionistRepository.findByReceptionistEmailIdAndReceptionistPassword(receptionist.receptionistEmailId, receptionist.receptionistPassword).orElseThrow(()->new ResourceNotFoundException("Receptionist ", "EmaildId",receptionist.receptionistEmailId+"and password "+receptionist.receptionistPassword));
	
	}

	@Override
	public Patient getPatientById(long patientId) throws ReceptionistNotFoundException {
		List<Receptionist> receptionits =  receptionistRepository.findAll();
		 System.out.println(receptionits);
		if(receptionits.isEmpty()) {
	
			throw new ReceptionistNotFoundException("OOPS!!!! Receptionist Not Found");
		}else {
		return patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Patient","PatientId",patientId));
		}
	}

	@Override
	public List<Patient> getAllPatients() throws ReceptionistNotFoundException {
		List<Receptionist> receptionits =  receptionistRepository.findAll();
		 System.out.println(receptionits);
		if(receptionits.isEmpty()) {
	
			throw new ReceptionistNotFoundException("OOPS!!!! Receptionist Not Found");
		}else {
		return patientRepository.findAll();
	}}

	@Override
	public Receptionist updateReceptionist(Receptionist receptionist, long receptionistId) {

		Receptionist existingUser = receptionistRepository.findById(receptionistId).orElseThrow(()->new ResourceNotFoundException("Receptionist","ReceptinistId",receptionistId));
		
		existingUser.setAddress(receptionist.getAddress());
		existingUser.setAge(receptionist.getAge());
		existingUser.setContactNumber(receptionist.getContactNumber());

		existingUser.setFirstName(receptionist.getFirstName());
		existingUser.setGender(receptionist.getGender());
		existingUser.setLastName(receptionist.getLastName());
		existingUser.setReceptionistEmailId(receptionist.getReceptionistEmailId());
		existingUser.setReceptionistPassword(receptionist.getReceptionistPassword());
		
		
		
		receptionistRepository.save(existingUser);
		
		return existingUser;
	}

	@Override
	public void deletePatient(long patientId) throws ReceptionistNotFoundException {
		 List<Receptionist> receptionits =  receptionistRepository.findAll();
		 System.out.println(receptionits);
		if(receptionits.isEmpty()) {
	
			throw new ReceptionistNotFoundException("OOPS!!!! Receptionist Not Found");
		}else {
		patientRepository.findById(patientId).orElseThrow(()->new NoSuchElementException());
		patientRepository.deleteById(patientId);
	}
	}

	@Override
	public List<Receptionist> getAllReceptionists() {
		
		return receptionistRepository.findAll();
	}

	@Override
	public Receptionist getReceptionistById(long receptionistId) {
	
		return receptionistRepository.findById(receptionistId).orElseThrow(()->new ResourceNotFoundException("Receptionist details","ReceptionistId",receptionistId));
	}

	@Override
	public void deleteReceptionist(long receptionistId) {

		
		receptionistRepository.findById(receptionistId).orElseThrow(()->new ResourceNotFoundException("Receptionist details","ReceptionistId",receptionistId));
		 
		System.out.println("deleted");
		receptionistRepository.deleteById(receptionistId);
	}

	@Override
	public Patient updatePatientAfterCouncelling(long patientId, long prescriptionId,long roomId) throws Exception {
		 List<Receptionist> receptionits =  receptionistRepository.findAll();
		 System.out.println(receptionits);
		if(receptionits.isEmpty()) {
	
		throw new ReceptionistNotFoundException("OOPS!!!! Receptionist Not Found");
		}
		else {
			Room room = roomService.getRoomById(roomId);
			Prescription getting = prescriptionService.getPrescriptionById(prescriptionId);
			
			BookingAppointment getAppointment = getting.getBookingAppointment();
			
			//BookingAppointment getAppoitnment = bookingAppointmentRepository.findById(getting.getBookingAppointment()).orElseThrow(()->new ResourceNotFoundException("Appointment details","AppointmentId",appointmentId));
			Patient oldPatient = patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Patient details","PatientId",patientId));
		 
			
			String date1=getAppointment.getAppointmentDate();
			
			Date admit = new SimpleDateFormat("yyyy/MM/dd").parse(date1);
			
			
			if(getting.getStatus().equals("admit")) {
				oldPatient.setAdmitDate(admit);
				List<Patient> patients = patientRepository.findAll();
				for(Patient patient:patients) {
					if(patient.getRoomId()==roomId)
					{
						throw new RoomDuplicateException("Room is already allocated with other patient");
					}
				}
				oldPatient.setRoomId(roomId);
			}
			 
			
			patientRepository.save(oldPatient); 
			
			return oldPatient;
		}
			
		
	}

	 

}
