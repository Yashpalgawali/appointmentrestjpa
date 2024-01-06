package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ActivityLogs;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;

@Service("empserv")
public class EmployeeServImpl implements EmployeeService {

	@Autowired
	EmployeeRepo emprepo;
	
	@Autowired
	ActivityService actserv;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	@Override
	public Employee saveEmployee(Employee emp) {
		Employee empl = emprepo.save(emp); 
		if(empl!=null) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Employee "+empl.getEmp_name()+" saved");
			act.setActivity_date(dtf.format(LocalDateTime.now()));
			actserv.saveActivity(act);
			return empl;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Employee "+emp.getEmp_name()+" is not saved");
			act.setActivity_date(dtf.format(LocalDateTime.now()));
			actserv.saveActivity(act);
			return empl;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> elist = emprepo.getAllEmployees();
		return elist;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		try {
			return emprepo.findById(id).get() ;
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateEmployee(Employee empl) {
		int res = emprepo.updateEmployee(empl.getEmp_name(), empl.getEmp_email(), empl.getEmp_status(), empl.getDepartment().getDept_id(), empl.getDesignation().getDesig_id(), empl.getEmp_id());
		if(res>0) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity(empl.getEmp_name()+" is updated successfully");
			act.setActivity_date(dtf.format(LocalDateTime.now()));
			actserv.saveActivity(act);
			return res ;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity(empl.getEmp_name()+" is not Updated");
			act.setActivity_date(dtf.format(LocalDateTime.now()));
			actserv.saveActivity(act);
		}	
		return res ;
	}

	@Override
	public  List<Employee>  getDeptByEmpId(String id) {
		List<Employee>  elist = emprepo.getDeptByEmpId(Long.valueOf(id));
		return elist;
	}

	@Override
	public Employee getempbyemail(String emp_email) {
		return emprepo.getEmployeByEmpEmail(emp_email);
	}

	@Override
	public Employee getEmployeeByName(String name) {
		return emprepo.getDeptByEmpName(name).get(0);
	}

}
