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
	

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime today = LocalDateTime.now();  
	
	@Override
	public Department saveDepartment(Department dept) {
		// TODO Auto-generated method stub
		Department depart = deptrepo.save(dept);
		if(depart!=null)
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+depart.getDept_name()+" is saved");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return depart;
		}
		else
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+dept.getDept_name()+" is not saved");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return depart;
		}
		
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
		int res = deptrepo.updateDepartmentByDeptId(dept.getDept_name(), dept.getCompany().getCompany_id(), dept.getDept_id());
				
		if(res>0)
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+dept.getDept_name()+" is updated");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Department "+dept.getDept_name()+" is not updated");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res;
		}
		
	}

}
