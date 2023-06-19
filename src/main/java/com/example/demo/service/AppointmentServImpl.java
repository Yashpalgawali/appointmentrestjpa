package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepo;

@Service("appointserv")
public class AppointmentServImpl implements AppointmentService {

	@Autowired
	AppointmentRepo appointrepo;
	
	@Autowired
	EmailService emailserv;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	LastUpdateTimeService lastupdatetimeserv;
	
	@Override
	public Appointment saveAppointment(Appointment appoint) {
		// TODO Auto-generated method stub
		
		Appointment apoint = appointrepo.save(appoint); 
		
		if(apoint!=null)
		{
			String base_url =	ServletUriComponentsBuilder
					.fromRequestUri(request)
					.replacePath(null)
					.build()
					.toUriString();
			
			String subject = appoint.getVis_purpose().substring(0, 10);
			
			String cnfappoint = base_url+"/confappointment/"+apoint.getAppoint_id();
			String declineappoint = base_url+"/declineappointment/"+apoint.getAppoint_id();
			
			emailserv.sendSimpleEmail(appoint.getEmployee().getEmp_email(), "Respected Sir/Ma'am,          "+appoint.getVis_name()
					+" needs an appointment regarding "+appoint.getVis_purpose().substring(0, 10)+" dated on "+appoint.getApdate()
					+"  "+appoint.getAptime()+"\n\n Confirm Appointment \n"+cnfappoint+"\n\n Decline Appointment \n"+declineappoint, subject);
			
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime today = LocalDateTime.now();  
		   
		   lastupdatetimeserv.updateLastUpdateTime(""+dtf.format(today));
		   return apoint;
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<Appointment> getAllAppointments() {
		// TODO Auto-generated method stub
		return appointrepo.getAllAppointments();
	}

	@Override
	public List<Appointment> getAllTodaysAppointments(String tday) {
		// TODO Auto-generated method stub
		return appointrepo.getAllTodaysAppointments(tday);
	}

	@Override
	public List<Appointment> getAllAppointmentsByEmail(String email) {
		// TODO Auto-generated method stub
		
		List<Appointment> elist = appointrepo.getAllEmployeesAppointments(email);
		List<Appointment> vlist = appointrepo.getAllVisitorAppointments(email);
		
		if(elist.size()>0){
			return elist;
		}
		else if(vlist.size()>0){
			return vlist;
		}
		else{	return null;  }
	}

	@Override
	public List<Appointment> getAllTodaysAppointmentsByEmail(String tdate, String email) {
		// TODO Auto-generated method stub
		
		List<Appointment> alist = appointrepo.getAllTodaysAppointmentsByEmail(tdate,email); 
		
		return alist;
	}

	@Override
	public int confAppointmentById(Long apid) {
		// TODO Auto-generated method stub
		return appointrepo.updateAppointmentStatusById(apid, "confirmed");
		
	}

	@Override
	public int declineAppointmentById(Long apid) {
		// TODO Auto-generated method stub
		return appointrepo.updateAppointmentStatusById(apid, "declined");
	}

	@Override
	public Appointment saveAdminAppointment(Appointment appoint) {
		// TODO Auto-generated method stub
		Appointment apoint = appointrepo.save(appoint); 
		
		if(apoint!=null)
		{
			String base_url =	ServletUriComponentsBuilder
					.fromRequestUri(request)
					.replacePath(null)
					.build()
					.toUriString();
			
			String subject = appoint.getVis_purpose().substring(0, 10);
			
			String cnfappoint = base_url+"/confappointment/"+apoint.getAppoint_id();
			String declineappoint = base_url+"/declineappointment/"+apoint.getAppoint_id();
			
			emailserv.sendSimpleEmail(appoint.getEmployee().getEmp_email(), "Respected Sir/Ma'am,          "+appoint.getVis_name()
					+" needs an appointment regarding "+appoint.getVis_purpose().substring(0, 10)+" dated on "+appoint.getApdate()
					+"  "+appoint.getAptime()+"\n\n Confirm Appointment \n"+cnfappoint+"\n\n Decline Appointment \n"+declineappoint, subject);
			
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime today = LocalDateTime.now();  
		   
		   lastupdatetimeserv.updateLastUpdateTime(""+dtf.format(today));
		   return apoint;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Appointment getAppointmentById(Long apid) {
		// TODO Auto-generated method stub
		try {
			return appointrepo.findById(apid).get();
		}
		catch(Exception e)
		{
			return null;
		}
		
	}

}