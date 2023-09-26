package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee emp);
	
	public List<Employee> getAllEmployees();
	
	//public Employee getEmployeeById(String id);
	
	public Employee getEmployeeById(Long id);
	
	public Employee getEmployeeByName(String name);
	
	public int updateEmployee(Employee empl);

	//public Employee getEmployeeByDeptId(String deptid);

	public  List<Employee> getDeptByEmpId(String id);

	public Employee getempbyemail(String emp_email);
	
}

