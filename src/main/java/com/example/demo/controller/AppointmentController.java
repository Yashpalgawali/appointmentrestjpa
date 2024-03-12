package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.EmailService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OtpService;

@RestController
@CrossOrigin("*")
@RequestMapping("appointment")
public class AppointmentController {

	@Autowired
	EmployeeService empserv;
	
	@Autowired
	AppointmentService appointserv;
	
	@Autowired
	EmailService emailserv;
	
	@Autowired
	OtpService otpserv;
	
	@Autowired
	Environment env;
	
	DateTimeFormatter dformat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	@GetMapping("/")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		return new ResponseEntity<List<Appointment>>(appointserv.getAllAppointments(),HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<List<Appointment>> saveAppointment(@RequestBody Appointment appoint, HttpSession sess )
	{	
		Appointment appointment = appointserv.saveAppointment(appoint);
		if(appointment!=null) {
			sess.setAttribute("vemail", appoint.getVis_email());
			return new  ResponseEntity<List<Appointment>>(appointserv.getAllAppointmentsByEmail(appoint.getVis_email()) ,HttpStatus.OK);
		}
		else {
			return new  ResponseEntity<List<Appointment>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{vemail}")
	public ResponseEntity<String> searchAppointmentByEmail(@PathVariable String vemail ,HttpServletRequest request ,HttpSession sess )
	{
		List<Appointment> appointment = appointserv.getAllAppointmentsByEmail(vemail);
		if(appointment!=null) {
			otpserv.generateotp(vemail);
			int otp = otpserv.getOtp(vemail);
			sess.setAttribute("otp", otp);
			sess.setAttribute("vemail", vemail);
			emailserv.sendSimpleEmail(vemail, "Hi, your otp is "+otp, "Verification OTP");
			
			return new ResponseEntity<String>(""+otp,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/appointmentbymail/{vemail}")
	public ResponseEntity<List<Appointment>> getAppointmentsByEmail(@PathVariable String vemail ,HttpServletRequest request ,HttpSession sess )
	{
		List<Appointment> appointment = appointserv.getAllAppointmentsByEmail(vemail);
		if(appointment!=null) {
			sess.setAttribute("vemail", vemail);
			return new ResponseEntity<List<Appointment>>(appointment,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
		}
	}
	// Fetch All Todays appointments for particular user
	@RequestMapping("/getallappointmentsbyemail/{email}")
	@ResponseBody
	public List<Appointment> getallappointmentsbyemail(@PathVariable("email") String email)
	{
		List<Appointment> aplist = appointserv.getAllAppointmentsByEmail(email);
		return aplist;
	}
	
	// Fetch Only Todays appointments for particular user
	@RequestMapping("/gettodaysappointmentsbyemail/{email}")
	@ResponseBody
	public List<Appointment> getTodaysAppointmentsByEmail(@PathVariable("email") String email)
	{
		List<Appointment> aplist = appointserv.getAllTodaysAppointmentsByEmail(dformat.format(LocalDate.now()),email);
		return aplist;
	}
	
	// Fetch All Todays appointments for admin
	@RequestMapping("/gettodaysappointments")
	@ResponseBody
	public List<Appointment> getTodaysAppointments()
	{
		List<Appointment> aplist = appointserv.getAllTodaysAppointments(dformat.format(LocalDate.now()));
		return aplist;
	}
	@GetMapping("/confappointment/{id}")
	public void confAppointmentById(@PathVariable("id")Long id) { 
		appointserv.confAppointmentById(id);
	}
	
	@GetMapping("/declineappointment/{id}")
	public void declineAppointmentById(@PathVariable("id")Long id)
	{ 
	 appointserv.confAppointmentById(id);
	}
}
