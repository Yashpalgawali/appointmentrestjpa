package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.example.demo.model.Department;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;

@RestController
@CrossOrigin("*")
@RequestMapping("department")
public class DepartmentController {

	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	Environment env;
	
	@Autowired
	CompanyService compserv;
	
	@PostMapping("/")
	public ResponseEntity<Department> saveDepartment(@RequestBody Department depart) {
		Department dept = deptserv.saveDepartment(depart);
		if(dept!=null) {
			return new ResponseEntity<Department>(dept,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Department>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Department>> getAllDepartments()
	{
		List<Department> dlist = deptserv.getAllDepartments();
		if(dlist.size()>0)
			return new ResponseEntity<List<Department>>(dlist,HttpStatus.OK);
		else
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("getdeptbycompid/{id}")
	public ResponseEntity<List<Department>> getDepartmentsByCompId(@PathVariable("id") Long id) {
		List<Department> deplist = deptserv.getAllDepartmentsByCompId(""+id);
		if(deplist.size()>0) {
			return new ResponseEntity<List<Department>>(deplist,HttpStatus.OK);
		}
		else
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("getdeptbycompname/{id}")
	public ResponseEntity<List<Department>> getDepartmentsByCompName(@PathVariable("id") String name) {
		List<Department> deplist = deptserv.getAllDepartmentsByCompName(name);
		if(deplist.size()>0) {
			return new ResponseEntity<List<Department>>(deplist,HttpStatus.OK);
		}
		else
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDeptByid(@PathVariable("id") String id) {
		Department dept = deptserv.getDeptByDeptId(id);
		if(dept!=null) {
			 return new ResponseEntity<Department>(dept,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	public  ResponseEntity<List<Department>> updateDepartment(@RequestBody Department depart) {
		int result = deptserv.updateDepartment(depart);
		if(result > 0) {
			 return new ResponseEntity<List<Department>> (deptserv.getAllDepartments(),HttpStatus.OK);
		}
		else {
			 return new ResponseEntity<List<Department>>(HttpStatus.OK);
		}
	}

}