package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import com.example.demo.service.EmployeeService;

@Controller
public class AppointmentController {

	@Autowired
	EmployeeService empserv;
	
	@Autowired
	AppointmentService appointserv;
	
	
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
	public String searchAppointmentByEmail(@ModelAttribute("Appointment") Appointment appoint,Model model , HttpServletRequest request , RedirectAttributes attr)
	{
		
		String base_url =	ServletUriComponentsBuilder
								.fromRequestUri(request)
								.replacePath(null)
								.build()
								.toUriString();
		model.addAttribute("baseurl", base_url);
		model.addAttribute("vemail", appoint.getVis_email());
		return "ViewAppointmentsByEmail" ; 
		
	}
	
	@PostMapping("/viewappointmentbyemail")
	public String viewAppointmentsByEmail()
	{
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
		System.err.println("Date -->"+ldate);
		List<Appointment> aplist = appointserv.getAllTodaysAppointmentsByEmail(ldate.toString(),email);
		
		System.err.println("Todays appointments are \n Size = "+aplist.size());
		aplist.stream().forEach(e->System.err.println(e));
		
		return aplist;
	}
	

}
