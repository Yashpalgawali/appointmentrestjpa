package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ActivityLogs;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepo;

@Service("deptserv")
public class DepartmentServImpl implements DepartmentService {

	@Autowired
	DepartmentRepo deptrepo;
	
	@Autowired
	ActivityService actserv;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	LocalDateTime today;  
	
	@Override
	public Department saveDepartment(Department dept) {
		Department depart = deptrepo.save(dept);
		if(depart!=null) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+depart.getDept_name()+" is saved");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return depart;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+dept.getDept_name()+" is not saved");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return depart;
		}
	}
	@Override
	public List<Department> getAllDepartmentsByCompId(String cid) {
		Long cmpid = Long.valueOf(cid);
		try {
			List<Department> deplist = deptrepo.getAllDepartmentsByCompId(cmpid);
			return deplist;
		}
		catch(Exception e) {
			return  null;
		}
	}
	@Override
	public Department getDeptByDeptId(String dept_id) {
		return deptrepo.getDepartmentByDeptId(Long.valueOf(dept_id));
	}

	@Override
	public int updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		int res = deptrepo.updateDepartmentByDeptId(dept.getDept_name(), dept.getCompany().getCompany_id(), dept.getDept_id());
		if(res>0) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+dept.getDept_name()+" is updated");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+dept.getDept_name()+" is not updated");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res;
		}
	}
	@Override
	public List<Department> getAllDepartments() {
		return deptrepo.getAllDepartments();
	}
	@Override
	public List<Department> getAllDepartmentsByCompName(String name) {
		return deptrepo.getAllDepartmentsByCompName(name);
	}
}
