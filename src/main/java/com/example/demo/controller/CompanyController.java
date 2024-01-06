package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.Company;
import com.example.demo.service.CompanyService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("company") 
public class CompanyController {

	@Autowired
	CompanyService compserv;
	
	@GetMapping("/")
	public ResponseEntity<List<Company>> viewCompanies() {
		List<Company> clist= compserv.getAllCOmpanies();
		if(clist.size()>0) {
			return new ResponseEntity<List<Company>>(clist,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Company> saveCompany(@RequestBody Company comp) {
		
		Company company = compserv.saveCompany(comp);
		if(company!=null) {
			return new ResponseEntity<Company>(company ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Company>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Company> editCompById(@PathVariable("id") Long id) {
		if(id!=null) {
			Company comp = compserv.getCompanyById(id);
			if(comp!=null) {
				return new ResponseEntity<Company>(comp,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
			}
		}
		else {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<List<Company>> updateCompany(@RequestBody Company company) {
		int result = compserv.updateCompany(company);
		if(result > 0) {
			return  new ResponseEntity<List<Company>>(compserv.getAllCOmpanies(), HttpStatus.OK);
		}
		else {
			return  new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/comp")
	public String getComp()
	{
		return "Called from ";
	}
//	@GetMapping("/viewcompanies")
//	public String viewCompanies(Model model)
//	{
//		List<Company> clist= compserv.getAllCOmpanies();
//		
//		model.addAttribute("clist", clist);
//		return "ViewCompany";
//	}
	
//	@GetMapping("/editcompbyid/{id}")
//	public String editCompById(@PathVariable("id") String id, Model model , RedirectAttributes attr) 
//	{
//		
//		if(id!="") {
//			Company comp = compserv.getCompanyById(id);
//			if(comp!=null)
//			{
//				model.addAttribute("comp", comp);
//				return "EditCompany";
//			}
//			else {
//				attr.addFlashAttribute("reserr", "No Company found for given ID");
//				return "redirect:/viewcompanies";
//			}
//			
//		}
//		else{
//			attr.addFlashAttribute("reserr", "No Company found for given ID");
//			return "redirect:/viewcompanies";
//		}
//	}
	
//	@RequestMapping("/updatecompany")
//	public String updateCompany(@ModelAttribute("Company")Company company,RedirectAttributes attr)
//	{
//		
//		int result = compserv.updateCompany(company);
//		if(result > 0)
//		{
//			attr.addFlashAttribute("response", "Company updated successfully");
//			return "redirect:/viewcompanies";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Company is not updated ");
//			return "redirect:/viewcompanies";
//		}
//		
//	}
}
