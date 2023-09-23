package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Employee;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DesignationService;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("employee")
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
	
	
	@PostMapping("/")
	public ResponseEntity<List<Employee>> saveEmployee(@RequestBody Employee emp) {
		System.err.println("Inside save employee controller \n"+emp.toString()+"\nDesignation = "+emp.getDesignation().getDesig_name());
		Employee empl = empserv.saveEmployee(emp);
		if(empl!=null) {
			return new ResponseEntity<List<Employee>>(empserv.getAllEmployees(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Employee>> viewEmployees() {
		List<Employee> elist = empserv.getAllEmployees();
		if(elist.size()>0){
			return new ResponseEntity<List<Employee>>(elist,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
		Employee emp = empserv.getEmployeeById(id);
		if(emp!=null) {
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/updateemployee")
	public ResponseEntity<List<Employee>> updateEmployee(@RequestBody Employee empl)
	{
		int res = empserv.updateEmployee(empl);
		if(res > 0) {
			return new ResponseEntity<List<Employee>>(empserv.getAllEmployees(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/getdeptbyempid/{id}")
	public  ResponseEntity<List<Employee>> getEmployeeByEmpId(@PathVariable("id")String id,Model model)  {
		List<Employee> elist = empserv.getDeptByEmpId(id);
		return new ResponseEntity<List<Employee>>(elist,HttpStatus.OK) ;
	}
	
//	@GetMapping("/addemployee")
//	public String addEmployee(Model model) {
//		List<Company> clist = compserv.getAllCOmpanies();
//		List<Designation> dlist = desigserv.getAllDesignations();
//		model.addAttribute("appname", env.getProperty("spring.application.name"));
//		model.addAttribute("clist", clist);
//		model.addAttribute("dlist", dlist);
//		return "AddEmployee";
//	}
	
//	@RequestMapping("/saveemployee")
//	public String saveEmployee(@ModelAttribute("Employee")Employee emp,RedirectAttributes attr) {
//		Employee empl = empserv.saveEmployee(emp);
//		if(empl!=null) {
//			attr.addFlashAttribute("response", "Employee Saved Successfully");
//			return "redirect:/viewemployee";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Employee is not saved ");
//			return "redirect:/viewemployee";
//		}
//	}
	
//	@GetMapping("/viewemployee")
//	public String viewEmployees(Model model) {
//		List<Employee> elist = empserv.getAllEmployees();
//		model.addAttribute("appname", env.getProperty("spring.application.name"));
//		model.addAttribute("elist", elist);
//		return "ViewEmployees";
//	}
	
//	@GetMapping("/editempbyid/{id}")
//	public String getEmployeeById(@PathVariable("id") String id, Model model, RedirectAttributes attr)
//	{
//		Employee emp = empserv.getEmployeeById(id);
//		if(emp!=null)
//		{
//			List<Company> clist = compserv.getAllCOmpanies();
//			List<Designation> dlist = desigserv.getAllDesignations();
//			model.addAttribute("emp", emp);
//			model.addAttribute("appname", env.getProperty("spring.application.name"));
//			model.addAttribute("clist", clist);
//			model.addAttribute("dlist", dlist);
//			return "EditEmployee";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Employee not found for given ID");
//			return "redirect:/viewemployee";
//		}
//	}
	
//	@RequestMapping("/updateemployee")
//	public String updateEmployee(@ModelAttribute("Employee")Employee empl,RedirectAttributes attr)
//	{
//		int res = empserv.updateEmployee(empl);
//		if(res > 0) {
//			attr.addFlashAttribute("response", "Employee is updated successfully");
//			return "redirect:/viewemployee";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Employee not found for given ID");
//			return "redirect:/viewemployee";
//		}
//	}
//	
//	@GetMapping("/getdeptbyempid/{id}")
//	@ResponseBody
//	public  List<Employee>  getEmployeeByEmpId(@PathVariable("id")String id,Model model)  {
//		List<Employee> elist = empserv.getDeptByEmpId(id);
//		model.addAttribute("appname", env.getProperty("spring.application.name"));
//		return elist;
//	}
}