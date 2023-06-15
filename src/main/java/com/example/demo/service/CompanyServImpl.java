package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepo;

@Service("compserv")
public class CompanyServImpl implements CompanyService {

	@Autowired
	CompanyRepo comprepo;
	
	@Override
	public Company saveCompany(Company comp) {
		// TODO Auto-generated method stub
		return comprepo.save(comp);
	}

	@Override
	public List<Company> getAllCOmpanies() {
		// TODO Auto-generated method stub
		return comprepo.findAll();
	}

	@Override
	public Company getCompanyById(String id) {
		// TODO Auto-generated method stub
		Long cid =Long.parseLong(id);
		
		return comprepo.findById(cid).get();
	}

	@Override
	public int updateCompany(Company comp) {
		// TODO Auto-generated method stub
		return comprepo.updateCompanyById(comp.getComp_name(), comp.getCompany_id());
	}

}
