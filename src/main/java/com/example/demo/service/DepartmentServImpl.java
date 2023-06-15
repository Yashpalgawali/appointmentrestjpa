package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepo;

@Service("deptserv")
public class DepartmentServImpl implements DepartmentService {

	@Autowired
	DepartmentRepo deptrepo;
	
	@Override
	public Department saveDepartment(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.save(dept);
	}

	@Override
	public List<Department> getAllDepartmentsByCompId(String cid) {
		// TODO Auto-generated method stub
		
		
		return deptrepo.getAllDepartmentsByCompId(cid);
	}

	@Override
	public Department getDeptByDeptId(String dept_id) {
		// TODO Auto-generated method stub
		
		Long did = Long.valueOf(dept_id);
		
		return deptrepo.getDepartmentByDeptId(did);
	}

	@Override
	public int updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.updateDepartmentByDeptId(dept.getDept_name(), dept.getCompany().getCompany_id(), dept.getDept_id());
	}

}
