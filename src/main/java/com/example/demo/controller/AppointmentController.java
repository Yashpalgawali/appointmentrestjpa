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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.example.demo.model.Appointment;
import com.example.demo.model.Employee;

import com.example.demo.service.AppointmentService;
import com.example.demo.service.EmailService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OtpService;

@Controller
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
	
	

	@GetMapping("/")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		return new ResponseEntity<List<Appointment>>(appointserv.getAllAppointments(),HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<List<Appointment>> saveAppointment(@RequestBody Appointment appoint, HttpSession sess )
	{	
		appoint.setStatus("pending");
		Appointment appointment = appointserv.saveAppointment(appoint);
		
		if(appointment!=null) {
			sess.setAttribute("vemail", appoint.getVis_email());
			return new  ResponseEntity<List<Appointment>>(appointserv.getAllAppointmentsByEmail(appoint.getVis_email()) ,HttpStatus.OK);
		}
		else {
			return new  ResponseEntity<List<Appointment>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/viewappointments")
	public String viewAppointments(Model model)
	{
		List<Appointment> aplist = appointserv.getAllAppointments();
		model.addAttribute("appname", env.getProperty("spring.application.name"));
		model.addAttribute("aplist", aplist);
		return "ViewAppointments";
	}
	
	
//	@GetMapping("/{vemail}")
//	public ResponseEntity<String> searchAppointmentByEmail(@PathVariable String vemail ,HttpServletRequest request ,HttpSession sess )
//	{
//		System.err.println("\n Inside search appointment byemail method\n Email= "+vemail);
//		List<Appointment> appointment = appointserv.getAllAppointmentsByEmail(vemail);
//		if(appointment!=null) {
//			otpserv.generateotp(vemail);
//			int otp = otpserv.getOtp(vemail);
//			sess.setAttribute("otp", otp);
//			sess.setAttribute("vemail", vemail);
//			emailserv.sendSimpleEmail(vemail, "Hi, your otp is "+otp, "Verification OTP");
//			
//			return new ResponseEntity<String>(HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/{vemail}")
	public ResponseEntity<String> searchAppointmentByEmail(@PathVariable String vemail ,HttpServletRequest request ,HttpSession sess )
	{
		System.err.println("\n Inside search appointment byemail method\n Email= "+vemail);
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
		System.err.println("\n Inside search appointment byemail method\n Email= "+vemail);
		List<Appointment> appointment = appointserv.getAllAppointmentsByEmail(vemail);
		if(appointment!=null) {
			
			sess.setAttribute("vemail", vemail);
			return new ResponseEntity<List<Appointment>>(appointment,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Appointment>>(HttpStatus.NOT_FOUND);
		}
	}
		
//	@PostMapping("/searchappointbyemail")
//	public String searchAppointmentByEmail(@ModelAttribute("Appointment") Appointment appoint,Model model , HttpServletRequest request ,HttpSession sess , RedirectAttributes attr)
//	{
//		String vemail = appoint.getVis_email();
//		List<Appointment> appointment = appointserv.getAllAppointmentsByEmail(vemail);
//		if(appointment!=null){
//			otpserv.generateotp(vemail);
//			int otp = otpserv.getOtp(vemail);
//			sess.setAttribute("otp", otp);
//			sess.setAttribute("vemail", vemail);
//			emailserv.sendSimpleEmail(vemail, "Hi, your otp is "+otp, "Verification OTP");
//			
//			attr.addFlashAttribute("response", "Otp sent to your email");
//			return "redirect:/confotp";
//		}
//		else{
//			attr.addFlashAttribute("reserr", "No appointment found for "+vemail);
//			return "redirect:/searchappointment";
//		}
//	}
	
	@GetMapping("/confotp")
	public String confOTP(){
		return "ConfirmOtp";
	}
	
	
	@PostMapping("/confotprl")
	public String validateOtp(@ModelAttribute("Appointment")Appointment appoint,HttpSession sess ,RedirectAttributes attr){
		int old_otp = (Integer)sess.getAttribute("otp");
		Integer nw_otp = appoint.getNew_otp();
		if(old_otp==nw_otp){
			otpserv.clearOtp(""+sess.getAttribute("vemail"));
			return "redirect:/viewappointmentbyemail";
		}
		else{
			attr.addFlashAttribute("reserr", "OTP didnt matched");
			return "redirect:/confotp";
		}
	} 
	
	@GetMapping("/viewappointmentbyemail")
	public String viewAppointmentsByEmail(HttpServletRequest request,Model model,HttpSession sess,RedirectAttributes attr){
		if(sess.getAttribute("vemail")!=null)
		{
			String base_url =	ServletUriComponentsBuilder
									.fromRequestUri(request)
									.replacePath(null)
									.build()
									.toUriString();
			model.addAttribute("baseurl", base_url);
			model.addAttribute("vemail", sess.getAttribute("vemail"));
			model.addAttribute("appname", env.getProperty("spring.application.name"));
			return "ViewAppointmentsByEmail";
		}
		else
		{
			attr.addFlashAttribute("reserr", "Please provide email ");
			return "redirect:/searchappointment";
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
		LocalDate ldate = LocalDate.now();
		List<Appointment> aplist = appointserv.getAllTodaysAppointmentsByEmail(ldate.toString(),email);
		return aplist;
	}
	
	
	// Fetch All Todays appointments for admin
	@RequestMapping("/gettodaysappointments")
	@ResponseBody
	public List<Appointment> getTodaysAppointments()
	{
		DateTimeFormatter dformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ldate = LocalDate.now();
		List<Appointment> aplist = appointserv.getAllTodaysAppointments(dformat.format(ldate));
		return aplist;
	}
	
	@GetMapping("/confappointment/{id}")
	public String confAppointmentById(@PathVariable("id")Long id,RedirectAttributes attr)
	{ 
		int res = appointserv.confAppointmentById(id);
		if(res>0)
		{
			attr.addFlashAttribute("response", "Appointment is confirmed");
			return "redirect:/";
		}
		else {
			attr.addFlashAttribute("reserr", "Appointment status is not updated");
			return "redirect:/";
		}
	}
	
	@GetMapping("/declineappointment/{id}")
	public String declineAppointmentById(@PathVariable("id")Long id,RedirectAttributes attr)
	{ 
		int res = appointserv.confAppointmentById(id);
		if(res>0)
		{
			attr.addFlashAttribute("reserr", "Appointment is declined");
			return "redirect:/";
		}
		else {
			attr.addFlashAttribute("reserr", "Appointment status is not updated");
			return "redirect:/";
		}
	}
	
}
