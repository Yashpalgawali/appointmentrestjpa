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
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee emp = empserv.getEmployeeById(id);
		if(emp!=null) {
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/")
	public ResponseEntity<List<Employee>> updateEmployee(@RequestBody Employee empl) {
		
		int res = empserv.updateEmployee(empl);
		if(res > 0) {
			return new ResponseEntity<List<Employee>>(empserv.getAllEmployees(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping("/getdeptbyempid/{id}")
	public  ResponseEntity<List<Employee>> getEmployeeByEmpId(@PathVariable("id")String id,Model model)  {
		List<Employee> elist = empserv.getDeptByEmpId(id);
		return new ResponseEntity<List<Employee>>(elist,HttpStatus.OK) ;
	}
}