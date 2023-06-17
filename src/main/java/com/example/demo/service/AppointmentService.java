package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.Appointment;

public interface AppointmentService {

	public Appointment saveAppointment(Appointment appoint);
	
	public List<Appointment> getAllAppointments();
	
	public List<Appointment> getAllTodaysAppointments(Date today);
	
	public List<Appointment> getAllAppointmentsByEmail(String email);
	
	public List<Appointment> getAllTodaysAppointmentsByEmail(String tdate ,String email);
	
	public int confAppointmentById(Long apid);
	
	public int declineAppointmentById(Long apid);
}
