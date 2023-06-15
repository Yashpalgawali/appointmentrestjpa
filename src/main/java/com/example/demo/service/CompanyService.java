package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Company;

public interface CompanyService {

	
	public Company saveCompany(Company comp);
	
	public List<Company> getAllCOmpanies();
	
	public Company getCompanyById(String id);
	
	public int updateCompany(Company comp);
	
}
