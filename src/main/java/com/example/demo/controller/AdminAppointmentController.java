package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("adminappointment")
public class AdminAppointmentController {

	@Autowired
	AppointmentService appointserv;
	
	@Autowired
	EmployeeService empserv;
	
	@Autowired
	Environment env;
	
	@GetMapping("/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id")Long id) {
		return new ResponseEntity<Appointment>(appointserv.getAppointmentById(id),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<Appointment>> getAllAppointment() {
		return new ResponseEntity<List<Appointment>>(appointserv.getAllAppointments(),HttpStatus.OK);
	}
	@PostMapping("/")
	public ResponseEntity<Appointment> saveAdminBookAppointment(@RequestBody Appointment appoint,HttpServletRequest request) {
		
		Appointment apt = appointserv.saveAppointment(appoint,request);
		if(apt!=null) {
			return new ResponseEntity<Appointment>(apt,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/")
	public ResponseEntity<List<Appointment>> updateAppointment(@RequestBody Appointment appoint) {
		int res = appointserv.updateAppointment(appoint);
		if(res>0) {
			return new ResponseEntity<List<Appointment>>(appointserv.getAllAppointments() ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_MODIFIED);
		}
	}
	@GetMapping("getcounts")
	public ResponseEntity<List<Integer>> getAppointmentCounts() {
		List<Integer> apcounts = new ArrayList<>();
		apcounts.add( appointserv.getConfirmedAppointmentCount());
		apcounts.add( appointserv.getDeclinedAppointmentCount() );
		apcounts.add( appointserv.getPendingAppointmentCount());
		apcounts.add( appointserv.getTotalAppointmentCount());
		
		return new ResponseEntity<List<Integer>>(apcounts,HttpStatus.OK);
	}
}
