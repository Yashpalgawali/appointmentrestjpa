package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Department;

public interface DepartmentService {

	public Department saveDepartment(Department dept);
	
	public List<Department> getAllDepartmentsByCompId(String cid);
	
	public Department getDeptByDeptId(String dept_id);
	
	public int updateDepartment(Department dept);
	
	public List<Department> getAllDepartments();
}
