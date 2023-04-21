package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.BookingAppointment;

public interface BookingAppointmentRepository  extends JpaRepository<BookingAppointment,Long> {
	
	public List<BookingAppointment> findBookingAppointmentByPatientId(long patientId);

}