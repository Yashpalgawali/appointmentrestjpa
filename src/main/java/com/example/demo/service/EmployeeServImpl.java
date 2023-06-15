package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;

@Service("empserv")
public class EmployeeServImpl implements EmployeeService {

	@Autowired
	EmployeeRepo emprepo;
	
	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return emprepo.save(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return emprepo.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(String id) {
		// TODO Auto-generated method stub
		
		Long eid = Long.parseLong(id);
		return emprepo.findById(eid).get() ;
	}

	@Override
	public int updateEmployee(Employee empl) {
		// TODO Auto-generated method stub
		return emprepo.updateEmployee(empl.getEmp_name(), empl.getEmp_email(), empl.getEmp_status(), empl.getDepartment().getDept_id(), empl.getDesignation().getDesig_id(), empl.getEmp_id());
	}

	@Override
	public Employee getDeptByEmpId(String id) {
		// TODO Auto-generated method stub
		return emprepo.getDeptByEmpId(id);
	}

}
