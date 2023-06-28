package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Company;
import com.example.demo.model.Designation;
import com.example.demo.model.Employee;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DesignationService;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	CompanyService compserv;
	
	@Autowired
	DesignationService desigserv;
	
	@Autowired
	EmployeeService empserv;
	
	@Autowired
	Environment env;
	
	@GetMapping("/addemployee")
	public String addEmployee(Model model)
	{
		List<Company> clist = compserv.getAllCOmpanies();
		
		List<Designation> dlist = desigserv.getAllDesignations();
		model.addAttribute("appname", env.getProperty("spring.application.name"));
		model.addAttribute("clist", clist);
		model.addAttribute("dlist", dlist);
		return "AddEmployee";
	}
	
	@RequestMapping("/saveemployee")
	public String saveEmployee(@ModelAttribute("Employee")Employee emp,RedirectAttributes attr)
	{
		Employee empl = empserv.saveEmployee(emp);
		
		if(empl!=null)
		{
			attr.addFlashAttribute("response", "Employee Saved Successfully");
			return "redirect:/viewemployee";
		}
		else {
			attr.addFlashAttribute("reserr", "Employee is not saved ");
			return "redirect:/viewemployee";
		}
	}
	
	@GetMapping("/viewemployee")
	public String viewEmployees(Model model)
	{
		List<Employee> elist = empserv.getAllEmployees();
		model.addAttribute("elist", elist);
		return "ViewEmployees";
	}
	
	
	@GetMapping("/editempbyid/{id}")
	public String getEmployeeById(@PathVariable("id") String id, Model model, RedirectAttributes attr)
	{
		Employee emp = empserv.getEmployeeById(id);
		if(emp!=null)
		{
			List<Company> clist = compserv.getAllCOmpanies();
			List<Designation> dlist = desigserv.getAllDesignations();
			model.addAttribute("emp", emp);
			model.addAttribute("clist", clist);
			model.addAttribute("dlist", dlist);
			return "EditEmployee";
		}
		else {
			attr.addFlashAttribute("reserr", "Employee not found for given ID");
			return "redirect:/viewemployee";
		}
	}
	
	@RequestMapping("/updateemployee")
	public String updateEmployee(@ModelAttribute("Employee")Employee empl,RedirectAttributes attr)
	{
		int res = empserv.updateEmployee(empl);
		if(res > 0)
		{
			attr.addFlashAttribute("response", "Employee is updated successfully");
			return "redirect:/viewemployee";
		}
		else {
			attr.addFlashAttribute("reserr", "Employee not found for given ID");
			return "redirect:/viewemployee";
		}
	}
	
	@GetMapping("/getdeptbyempid/{id}")
	@ResponseBody
	public Employee getEmployeeByEmpId(@PathVariable("id")String id) 
	{
		return empserv.getDeptByEmpId(id);
	}
}
