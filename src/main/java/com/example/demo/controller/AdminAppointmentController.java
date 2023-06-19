package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.EmployeeService;

@Controller
public class AdminAppointmentController {

	@Autowired
	AppointmentService appointserv;
	
	@GetMapping("/adminbookappoint")
	public String adminBookAppointment()
	{
		return "AdminBookAppointment";
	}
	
	@PostMapping("/saveadminappointment")
	public String saveAdminBookAppointment(@ModelAttribute("Appointment") Appointment appoint,RedirectAttributes attr)
	{
		Appointment apt = appointserv.saveAppointment(appoint);
		
		if(apt!=null){
			attr.addFlashAttribute("reswait", "Appointment is saved successfully and waiting for confirmation");
			return "redirect:/viewadminappointments";
		}
		else{
			attr.addFlashAttribute("reserr", "Appointment is not saved");
			return "redirect:/viewadminappointments";
		}
	}
	
	@GetMapping("adminviewappoints")
	public String adminViewAppointments(Model model){
		return "AdminViewAppointments";
	}
	
	@Autowired
	EmployeeService empserv;
	
	@Autowired
	Environment env;
	
	@GetMapping("/editappointbyid/{id}")
	public String getAppointmentById(@PathVariable("id")String id,Model model){
		Long apid = Long.valueOf(id);
		model.addAttribute("elist", empserv.getAllEmployees());
		model.addAttribute("appname", env.getProperty("spring.application.name"));
		model.addAttribute("appoint", appointserv.getAppointmentById(apid));
		return "EditAppointment";
	}
	
	@PostMapping("/updateappointment")
	public String updateAppointment(@ModelAttribute("Appointment") Appointment appoint,RedirectAttributes attr)
	{
		int res = appointserv.updateAppointment(appoint);
		if(res>0)
		{
			attr.addFlashAttribute("response", "Appointment is Updated successfully");
			return "redirect:/viewadminappointments";
		}
		else {
			attr.addFlashAttribute("reserr", "Appointment is not Updated");
			return "redirect:/viewadminappointments";
		}
	}
}
