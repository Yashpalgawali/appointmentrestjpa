package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository("emprepo")
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Transactional
	@Modifying
	@Query(value="UPDATE Employee e SET e.emp_name=:ename,e.emp_email=:email,e.department.dept_id=:depid,e.designation.desig_id=:desid,e.emp_status=:status WHERE e.emp_id=:empid")
	public int updateEmployee(String ename,String email,Long status,Long depid,Long desid,Long empid);
	
	
	@Query("SELECT e FROM Employee e JOIN e.designation JOIN e.department JOIN e.department.company")
	public List<Employee> getAllEmployees();
	
//	@Query(value="SELECT * FROM tbl_employee JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.company_id=tbl_department.company_id WHERE tbl_employee.emp_id=?1",nativeQuery = true)
	@Query("SELECT e FROM Employee e JOIN e.designation JOIN e.department.company WHERE e.emp_id=?1")
	public  List<Employee>  getDeptByEmpId(Long empid);
	
	@Query("SELECT e FROM Employee e JOIN e.designation JOIN e.department.company WHERE e.emp_name=?1")
	public  List<Employee>  getDeptByEmpName(String name);
	
	
	@Query("SELECT e FROM Employee e WHERE e.emp_email=:email")
	public Employee getEmployeByEmpEmail(String email);
	
}
