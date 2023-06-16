package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	CompanyService compserv;
	
	
	@GetMapping("/addcompany")
	public String addCompany()
	{
		return "AddCompany";
	}
	
	@RequestMapping("/savecompany")
	public String saveCompany(@ModelAttribute("Company")Company comp,BindingResult br , RedirectAttributes attr)
	{
		
		
		Company company = compserv.saveCompany(comp);
		if(company!=null)
		{
			attr.addFlashAttribute("response", "Company is saved successfully");
			return "redirect:/viewcompanies";
		}
		else {
			attr.addFlashAttribute("reserr", "Company is not saved");
			return "redirect:/viewcompanies";
		}
	}
	
	@GetMapping("/viewcompanies")
	public String viewCompanies(Model model)
	{
		List<Company> clist= compserv.getAllCOmpanies();
		model.addAttribute("clist", clist);
		return "ViewCompany";
	}
	
	@GetMapping("/editcompbyid/{id}")
	public String editCompById(@PathVariable("id") String id, Model model , RedirectAttributes attr) 
	{
		
		if(id!="") {
			
			Company comp = compserv.getCompanyById(id);
			if(comp!=null)
			{
				model.addAttribute("comp", comp);
				return "EditCompany";
			}
			else {
				attr.addFlashAttribute("reserr", "No Company found for given ID");
				return "redirect:/viewcompanies";
			}
			
		}
		else{
			attr.addFlashAttribute("reserr", "No Company found for given ID");
			return "redirect:/viewcompanies";
		}
	}
	
	@RequestMapping("/updatecompany")
	public String updateCompany(@ModelAttribute("Company")Company company,RedirectAttributes attr)
	{
		
		int result = compserv.updateCompany(company);
		if(result > 0)
		{
			attr.addFlashAttribute("response", "Company updated successfully");
			return "redirect:/viewcompanies";
		}
		else {
			attr.addFlashAttribute("reserr", "Company is not updated ");
			return "redirect:/viewcompanies";
		}
		
	}
}
