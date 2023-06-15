package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;

@Repository("comprepo")
public interface CompanyRepo extends JpaRepository<Company, Long> {

	
	@Transactional
	@Modifying
	@Query(value="update tbl_company set comp_name=?1 where company_id=?2", nativeQuery = true)
	public Integer updateCompanyById(String cname, Long cid);
	
}
