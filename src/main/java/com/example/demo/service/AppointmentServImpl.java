package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.ActivityLogs;
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
	
	@Autowired
	ActivityService actserv;
	
	@Autowired
	Environment env;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime today = LocalDateTime.now();  
	
	@Override
	public Appointment saveAppointment(Appointment appoint) {
		// TODO Auto-generated method stub
		String appname = env.getProperty("spring.application.name");
		Appointment apoint = appointrepo.save(appoint); 
		
		if(apoint!=null)
		{  
			lastupdatetimeserv.updateLastUpdateTime(""+dtf.format(today));
			
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Appointment is saved for "+apoint.getVis_email());
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			
			String base_url =	ServletUriComponentsBuilder
					.fromRequestUri(request)
					.replacePath(null)
					.build()
					.toUriString();
			
			String subject = appoint.getVis_purpose().substring(0, 10);
			
			String cnfappoint = base_url+"/"+appname+"/confappointment/"+apoint.getAppoint_id();
			String declineappoint = base_url+"/"+appname+"/declineappointment/"+apoint.getAppoint_id();
			
			emailserv.sendSimpleEmail(appoint.getEmployee().getEmp_email(), "Respected Sir/Ma'am,          "+appoint.getVis_name()
					+" needs an appointment regarding "+appoint.getVis_purpose().substring(0, 10)+" dated on "+appoint.getApdate()
					+"  "+appoint.getAptime()+"\n\n Confirm Appointment \n"+cnfappoint+"\n\n Decline Appointment \n"+declineappoint, subject);
			
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
		if(elist.size()>0)
		{
			return elist;
		}
		
		List<Appointment> vlist = appointrepo.getAllVisitorAppointments(email);
		if(vlist.size()>0){
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
		
		Appointment appoint = appointrepo.findById(apid).get();
		
		int res = appointrepo.updateAppointmentStatusById(apid, "confirmed");
		if(res>0)
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Appointment is confirmed for "+appoint.getVis_email());
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			
			emailserv.sendSimpleEmail(appoint.getEmployee().getEmp_email(), "Respected Sir/Ma'am,          \n Your appointment is confirmed with "+appoint.getEmployee().getEmp_name()+" dated on "+appoint.getApdate()+" "+appoint.getAptime() , "Appointment Confirmation");
	
			return res;
		}
		else
			return res ;
		
	}

	@Override
	public int declineAppointmentById(Long apid) {
		// TODO Auto-generated method stub
		Appointment appoint = appointrepo.findById(apid).get();
		int res = appointrepo.updateAppointmentStatusById(apid, "declined");
		
		if(res>0){
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Appointment is declined for "+appoint.getVis_email());
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			
			emailserv.sendSimpleEmail(appoint.getEmployee().getEmp_email(), "Respected Sir/Ma'am,          \n Your appointment is declined with "+appoint.getEmployee().getEmp_name()+" dated on "+appoint.getApdate()+" "+appoint.getAptime() , "Appointment Confirmation");
			return res;
		}
		else{
			return res;
		}
	}

	@Override
	public Appointment saveAdminAppointment(Appointment appoint) {
		// TODO Auto-generated method stub
		Appointment apoint = appointrepo.save(appoint); 
		
		if(apoint!=null)
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime today = LocalDateTime.now();  
			   
			lastupdatetimeserv.updateLastUpdateTime(""+dtf.format(today));
			   
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Appointment is saved for "+apoint.getVis_email()+" by admin");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			
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
			
		   return apoint;
		}
		else{
			return null;
		}
	}

	@Override
	public Appointment getAppointmentById(Long apid) {
		// TODO Auto-generated method stub
		try {
			return appointrepo.findById(apid).get();
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public int updateAppointment(Appointment appoint) {
		// TODO Auto-generated method stub
		return appointrepo.updateAppointmentById(appoint.getApdate(), appoint.getAptime(), appoint.getVcomp_name(), appoint.getVis_contact(), appoint.getVis_email(), appoint.getVis_name(), appoint.getVis_purpose(), appoint.getEmployee().getEmp_id(), appoint.getStatus(), appoint.getAppoint_id());
	}

	@Override
	public int getTotalAppointmentCount() {
		// TODO Auto-generated method stub
		return appointrepo.getTotalAppointmentCount();
	}

	@Override
	public int getPendingAppointmentCount() {
		// TODO Auto-generated method stub
		return appointrepo.getPendingAppointmentCount();
	}

	@Override
	public int getConfirmedAppointmentCount() {
		// TODO Auto-generated method stub
		return appointrepo.getConfirmedAppointmentCount();
	}

	@Override
	public int getDeclinedAppointmentCount() {
		// TODO Auto-generated method stub
		return appointrepo.getDeclinedAppointmentCount();
	}

}
