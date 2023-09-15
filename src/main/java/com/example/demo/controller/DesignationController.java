package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Designation;
import com.example.demo.service.DesignationService;

@RestController
@CrossOrigin
@RequestMapping("designation")
public class DesignationController {

	@Autowired
	DesignationService desigserv;
	

	@PostMapping("/")
	public ResponseEntity<Designation> saveDesignation(@RequestBody Designation desig) 	{
		Designation designation = desigserv.saveDesignation(desig);
		
		if(designation!=null) {
			return new ResponseEntity<Designation>(designation,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Designation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Designation>> viewDesignations(Model model) {
		List<Designation> dlist = desigserv.getAllDesignations();
		if(dlist.size()>0) {
			return new ResponseEntity<List<Designation>>(dlist,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Designation> editDesignationById(@PathVariable("id") String id) {
		
		Designation desig = desigserv.getDesignationByid(id);
		if(desig!=null) {
			return new ResponseEntity<Designation>(desig,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PutMapping("/")
	public ResponseEntity<List<Designation>> updateDesignation(@RequestBody Designation desig)
	{
		int result = desigserv.updateDesignation(desig);
		if(result>0) {
			return new ResponseEntity<List<Designation>>(desigserv.getAllDesignations(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Designation>>(HttpStatus.NOT_MODIFIED);
		}
	}
//	@RequestMapping("/savedesignation")
//	public String saveDesignation(@ModelAttribute("Designation")Designation desig,RedirectAttributes attr )
//	{
//		Designation designation = desigserv.saveDesignation(desig) ;
//		
//		if(designation!=null) {
//			attr.addFlashAttribute("response", "Designation is saved Successfully");
//			return "redirect:/viewdesignations";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Designation is not saved");
//			return "redirect:/viewdesignations";
//		}
//	}
	
//	@GetMapping("/viewdesignations")
//	public String viewDesignations(Model model)
//	{
//		List<Designation> dlist = desigserv.getAllDesignations();
//				
//		model.addAttribute("dlist", dlist);
//		return "ViewDesignation";
//	}
//	
//	@RequestMapping("/editdesigbyid/{id}")
//	public String editDesignationById(@PathVariable("id") String id, Model model,RedirectAttributes attr) {
//		
//		Designation desig = desigserv.getDesignationByid(id);
//		if(desig!=null) {
//			model.addAttribute("desig", desig);
//			return "EditDesignation";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "No Designation found for given ID");
//			return "redirect:/viewdesignations";
//		}
//	}
	
//	@RequestMapping("/updatedesignation")
//	public String updateDesignation(@ModelAttribute("Designation")Designation desig,RedirectAttributes attr)
//	{
//		int result = desigserv.updateDesignation(desig);
//		if(result>0)
//		{
//			attr.addFlashAttribute("response", "Designation is Updated successfully...");
//			return "redirect:/viewdesignations";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Designation is not updated");
//			return "redirect:/viewdesignations";
//		}
//	}

	
}
