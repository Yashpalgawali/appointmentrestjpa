package com.example.demo.controller;


import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AppointmentController {

	@Autowired
	EmployeeService empserv;
	
	@Autowired
	AppointmentService appointserv;
	
	@Autowired
	EmailService emailserv;
	
	@Autowired
	OtpService otpserv;
	
	@GetMapping("/bookappointment")
	public String bookAppointment(Model model)
	{
		List<Employee> elist = empserv.getAllEmployees();
		
		model.addAttribute("elist", elist);
		return "BookAppointment";
	}
	
	@RequestMapping("/saveappointment")
	public String saveAppointment(@ModelAttribute("Appointment")Appointment appoint,RedirectAttributes attr)
	{
		
		appoint.setStatus("pending");
		Appointment appointment = appointserv.saveAppointment(appoint);
		
		if(appointment!=null)
		{
			attr.addFlashAttribute("response", "Appointment is booked and waiting for the confirmation");
			return "redirect:/viewappointments";
		}
		else {
			attr.addFlashAttribute("reserr", "Appointment is not booked ");
			return "redirect:/viewappointments";
		}
	}
	
	@GetMapping("/viewappointments")
	public String viewAppointments(Model model)
	{
		List<Appointment> aplist = appointserv.getAllAppointments();
		
		model.addAttribute("aplist", aplist);
		return "ViewAppointments";
	}
	
	
	@RequestMapping("/getallappointments")
	@ResponseBody
	public List<Appointment> getAllAppointments()
	{
		return appointserv.getAllAppointments();
	}
	
	
	@GetMapping("/searchappointment")
	public String searchAppointment()
	{
		return "SearchAppointment";
	}
	
		
	@PostMapping("/searchappointbyemail")
	public String searchAppointmentByEmail(@ModelAttribute("Appointment") Appointment appoint,Model model , HttpServletRequest request ,HttpSession sess , RedirectAttributes attr)
	{
		String vemail = appoint.getVis_email();
		
		List<Appointment> appointment = appointserv.getAllAppointmentsByEmail(vemail);
		
		if(appointment!=null) 
		{
			otpserv.generateotp(vemail);
			int otp = otpserv.getOtp(vemail);
			sess.setAttribute("otp", otp);
			sess.setAttribute("vemail", vemail);
			emailserv.sendSimpleEmail(vemail, "Hi, your otp is "+otp, "Verification OTP");
			
			attr.addFlashAttribute("response", "Otp sent to your email");
			return "redirect:/confotp";
		}
		else
		{
			attr.addFlashAttribute("reserr", "No appointment found for "+vemail);
			return "redirect:/searchappointment";
		}
		
	}
	
	@GetMapping("/confotp")
	public String confOTP(Model model)
	{
		return "ConfirmOtp";
	}
	
	
	@PostMapping("/confotprl")
	public String validateOtp(@ModelAttribute("Appointment")Appointment appoint,HttpSession sess ,RedirectAttributes attr)
	{
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
	public String viewAppointmentsByEmail(HttpServletRequest request,Model model,HttpSession sess)
	{
		
		String base_url =	ServletUriComponentsBuilder
								.fromRequestUri(request)
								.replacePath(null)
								.build()
								.toUriString();
		
		System.err.println("Base URL->> "+base_url+"\n Email ->> "+sess.getAttribute("vemail"));
		
		model.addAttribute("baseurl", base_url);
		model.addAttribute("vemail", sess.getAttribute("vemail"));
		return "ViewAppointmentsByEmail";
	}
	
	@RequestMapping("/getallappointmentsbyemail/{email}")
	@ResponseBody
	public List<Appointment> getallappointmentsbyemail(@PathVariable("email") String email)
	{
		List<Appointment> aplist = appointserv.getAllAppointmentsByEmail(email);
		return aplist;
	}
	
	@RequestMapping("/gettodaysappointmentsbyemail/{email}")
	@ResponseBody
	public List<Appointment> getTodaysAppointmentsByEmail(@PathVariable("email") String email)
	{
		LocalDate ldate = LocalDate.now();
	
		List<Appointment> aplist = appointserv.getAllTodaysAppointmentsByEmail(ldate.toString(),email);
		return aplist;
	}
	

	
	@GetMapping("testmail")
	@ResponseBody
	public String testmail()
	{
//		otpserv.generateotp("yashpal");
//		int otp = otpserv.getOtp("yashpal");
//		emailserv.sendSimpleEmail("crankyash@gmail.com", "This is a test mail OTP is "+otp, "Test mail");
		return "";
	}
}
