package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ActivityLogs;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepo;

@Service("compserv")
public class CompanyServImpl implements CompanyService {

	@Autowired
	CompanyRepo comprepo;
	
	@Autowired
	ActivityService actserv;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	LocalDateTime today = LocalDateTime.now();
	
	@Override
	public Company saveCompany(Company comp) {
	
		Company cmpny = comprepo.save(comp);
		if(cmpny!=null) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Company "+comp.getComp_name()+" saved");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return cmpny;
		}
		else {
			return cmpny;
		}
	}

	@Override
	public List<Company> getAllCOmpanies() {
		return comprepo.findAll();
	}

	@Override
	public Company getCompanyById(String id) {
		Long cid =Long.parseLong(id);
		try {
			return comprepo.findById(cid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateCompany(Company comp) {
		
		int res = comprepo.updateCompanyById(comp.getComp_name(), comp.getCompany_id());
		
		if(res>0) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Company "+comp.getComp_name()+" updated");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res ;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Company "+comp.getComp_name()+" not updated");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res ;
		}
	}

}
