package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepo;

@Service("appointserv")
public class AppointmentServImpl implements AppointmentService {

	@Autowired
	AppointmentRepo appointrepo;
	
	@Override
	public Appointment saveAppointment(Appointment appoint) {
		// TODO Auto-generated method stub
		return appointrepo.save(appoint);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		// TODO Auto-generated method stub
		return appointrepo.getAllAppointments();
	}

	@Override
	public List<Appointment> getAllTodaysAppointments(Date tday) {
		// TODO Auto-generated method stub
		return appointrepo.getAllTodaysAppointments(tday);
	}

	@Override
	public List<Appointment> getAllAppointmentsByEmail(String email) {
		// TODO Auto-generated method stub
		
		List<Appointment> elist = appointrepo.getAllEmployeesAppointments(email);
		List<Appointment> vlist = appointrepo.getAllVisitorAppointments(email);
		
		if(elist.size()>0)
		{
			return elist;
		}
		else if(vlist.size()>0)
		{
			return vlist;
		}
		else
			{	return null;  }
	}

	@Override
	public List<Appointment> getAllTodaysAppointmentsByEmail(String tdate, String email) {
		// TODO Auto-generated method stub
		
		List<Appointment> alist = appointrepo.getAllTodaysAppointmentsByEmail(tdate,email); 
		
		return alist;
	}

}
