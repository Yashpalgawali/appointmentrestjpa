package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.Appointment;

public interface AppointmentService {

	public Appointment saveAppointment(Appointment appoint);
	
	public List<Appointment> getAllAppointments();
	
	public List<Appointment> getAllTodaysAppointments(String today);
	
	public List<Appointment> getAllAppointmentsByEmail(String email);
	
	public List<Appointment> getAllTodaysAppointmentsByEmail(String tdate ,String email);
	
	public int confAppointmentById(Long apid);
	
	public int declineAppointmentById(Long apid);
	
	public Appointment saveAdminAppointment(Appointment appoint);
	
	public Appointment getAppointmentById(Long apid);
	
	public int updateAppointment(Appointment appoint); 

	public int getTotalAppointmentCount();
	
	public int getPendingAppointmentCount();
	
	public int getConfirmedAppointmentCount();
	
	public int getDeclinedAppointmentCount();
	
}
