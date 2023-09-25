package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@CrossOrigin
@RequestMapping("adminappointment")
public class AdminAppointmentController {

	@Autowired
	AppointmentService appointserv;
	
	@Autowired
	EmployeeService empserv;
	
	@Autowired
	Environment env;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id")Long id){

		return new ResponseEntity<Appointment>(appointserv.getAppointmentById(id),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Appointment>> getAllAppointment(){

		return new ResponseEntity<List<Appointment>>(appointserv.getAllAppointments(),HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Appointment> saveAdminBookAppointment(@RequestBody Appointment appoint)
	{
		Appointment apt = appointserv.saveAppointment(appoint);
		if(apt!=null) {
			return new ResponseEntity<Appointment>(apt,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appoint) {
		int res = appointserv.updateAppointment(appoint);
		if(res>0) {
			return new ResponseEntity<Appointment>(appointserv.getAppointmentById(appoint.getAppoint_id()) ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Appointment>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping("adminhome")
	public String adminHome(Model model,HttpSession sess)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		List<Appointment> aplist = appointserv.getAllAppointments();
		//Stream<Appointment> aptstr = aplist.stream();
		long tot_count  = aplist.stream().count();
		long pending = aplist.stream().filter(apt->apt.getStatus().equals("pending")).count();
		long confirm = aplist.stream().filter(apt->apt.getStatus().equals("confirmed")).count();
		
		long decline = aplist.stream().filter(apt->apt.getStatus().equals("declined")).count();
		
		sess.setAttribute("username", auth.getName());
		
		model.addAttribute("tot_count", tot_count);
		model.addAttribute("pending_count", pending);
		model.addAttribute("confirm_count", confirm);
		model.addAttribute("decline_count", decline );

		Map<String, Long> cntmap =null;
		
		cntmap.put("tot_count", tot_count);
		cntmap.put("pending", pending);
		cntmap.put("confirm", confirm);
		cntmap.put("decline", decline);
		
		
		return "AdminHome";
	}
	
	@GetMapping("getcounts")
	public ResponseEntity<List<Integer>> getAppointmentCounts()
	{
		List<Integer> apcounts = new ArrayList<>();
		apcounts.add( appointserv.getConfirmedAppointmentCount());
		apcounts.add( appointserv.getDeclinedAppointmentCount() );
		apcounts.add( appointserv.getPendingAppointmentCount());
		apcounts.add( appointserv.getTotalAppointmentCount());
		apcounts.stream().forEach(e->System.err.println(e));
		return new ResponseEntity<List<Integer>>(apcounts,HttpStatus.OK);
	}
	
	@GetMapping("/adminbookappoint")
	public String adminBookAppointment(Model model,HttpSession sess)
	{
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		sess.setAttribute("username", auth.getName()) ;
		model.addAttribute("appname", env.getProperty("spring.application.name"));
		model.addAttribute("elist", empserv.getAllEmployees() );
		return "AdminBookAppointment";
	}
	
//	@PostMapping("/saveadminappointment")
//	public String saveAdminBookAppointment(@ModelAttribute("Appointment") Appointment appoint,RedirectAttributes attr)
//	{
//		Appointment apt = appointserv.saveAppointment(appoint);
//		
//		if(apt!=null) {
//			attr.addFlashAttribute("response", "Appointment is saved successfully and waiting for confirmation");
//			return "redirect:/adminviewappoints";
//		}
//		else{
//			attr.addFlashAttribute("reserr", "Appointment is not saved");
//			return "redirect:/adminviewappoints";
//		}
//	}
	
	@GetMapping("adminviewappoints")
	public String adminViewAppointments(Model model,HttpSession sess){
		model.addAttribute("appname", env.getProperty("spring.application.name"));
		String admemail = (String) sess.getAttribute("username");
		model.addAttribute("admemail", admemail);
		return "AdminViewAppointments";
	}
	
//	@GetMapping("/editappointbyid/{id}")
//	public String getAppointmentById(@PathVariable("id")Long id,Model model){
//	//	Long apid = Long.valueOf(id);
//		model.addAttribute("elist", empserv.getAllEmployees());
//		model.addAttribute("appname", env.getProperty("spring.application.name"));
//		model.addAttribute("appoint", appointserv.getAppointmentById(id));
//		return "EditAppointment";
//	}
	
//	@PostMapping("/updateappointment")
//	public String updateAppointment(@ModelAttribute("Appointment") Appointment appoint,RedirectAttributes attr)
//	{
//		int res = appointserv.updateAppointment(appoint);
//		if(res>0)
//		{
//			attr.addFlashAttribute("response", "Appointment is Updated successfully");
//			return "redirect:/adminviewappoints";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Appointment is not Updated");
//			return "redirect:/adminviewappoints";
//		}
//	}
}
