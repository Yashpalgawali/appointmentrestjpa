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

	
	@Query(value="select * from tbl_department join tbl_company on tbl_company.company_id=tbl_department.company_id where tbl_department.company_id=?1" , nativeQuery = true)
	public List<Department> getAllDepartmentsByCompId(String cid);
	
	@Query(value="select * from tbl_department join tbl_company on tbl_company.company_id=tbl_department.company_id where tbl_department.dept_id=?1" , nativeQuery = true)
	public Department getDepartmentByDeptId(Long did);
	
	
	@Transactional
	@Modifying
	@Query(value="update tbl_department set dept_name=?1, company_id=?2 where dept_id=?3" , nativeQuery = true)
	public int updateDepartmentByDeptId(String dname, Long cid, Long did);
	
}
