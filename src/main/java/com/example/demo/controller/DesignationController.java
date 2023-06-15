package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Designation;
import com.example.demo.service.DesignationService;

@Controller
public class DesignationController {

	@GetMapping("/adddesignation")
	public String addDesignation()
	{
		return "AddDesignation";
	}
	
	@Autowired
	DesignationService desigserv;
	
	@RequestMapping("/savedesignation")
	public String saveDesignation(@ModelAttribute("Designation")Designation desig,RedirectAttributes attr )
	{
		Designation designation = desigserv.saveDesignation(desig) ;
		
		if(designation!=null) {
			attr.addFlashAttribute("response", "Designation is saved Successfully");
			return "redirect:/viewdesignations";
		}
		else {
			attr.addFlashAttribute("reserr", "Designation is not saved");
			return "redirect:/viewdesignations";
		}
	}
	
	@GetMapping("/viewdesignations")
	public String viewDesignations(Model model)
	{
		List<Designation> dlist = desigserv.getAllDesignations();
				
		model.addAttribute("dlist", dlist);
		return "ViewDesignation";
	}
	
	@RequestMapping("/editdesigbyid/{id}")
	public String editDesignationById(@PathVariable("id") String id, Model model,RedirectAttributes attr) {
		
		Designation desig = desigserv.getDesignationByid(id);
		if(desig!=null) {
			model.addAttribute("desig", desig);
			return "EditDesignation";
		}
		else {
			attr.addFlashAttribute("reserr", "No Designation found for given ID");
			return "redirect:/viewdesignations";
		}
	}
	
	@RequestMapping("/updatedesignation")
	public String updateDesignation(@ModelAttribute("Designation")Designation desig,RedirectAttributes attr)
	{
		int result = desigserv.updateDesignation(desig);
		if(result>0)
		{
			attr.addFlashAttribute("response", "Designation is Updated successfully...");
			return "redirect:/viewdesignations";
		}
		else {
			attr.addFlashAttribute("reserr", "Designation is not updated");
			return "redirect:/viewdesignations";
		}
	}

	
}
