package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Department;

@Repository("departrepo")
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	
	@Query("SELECT d FROM Department d JOIN  d.company c WHERE d.company.company_id=?1" )
	public List<Department> getAllDepartmentsByCompId(Long cid);
	
	
	@Query("SELECT d FROM Department d JOIN  d.company c WHERE d.company.comp_name=?1" )
	public List<Department> getAllDepartmentsByCompName(String name);
	
	
	@Query("SELECT d FROM Department d JOIN d.company  WHERE d.dept_id=?1")
	public Department getDepartmentByDeptId(Long did);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE Department d SET d.dept_name=?1, d.company.company_id=?2 WHERE d.dept_id=?3")
	public int updateDepartmentByDeptId(String dname, Long cid, Long did);
	
	@Query("SELECT d FROM Department d JOIN d.company")
	public List<Department> getAllDepartments();
	
}
