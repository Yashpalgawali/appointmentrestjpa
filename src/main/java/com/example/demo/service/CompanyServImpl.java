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
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime today = LocalDateTime.now();  
	   
	
	@Override
	public Company saveCompany(Company comp) {
		// TODO Auto-generated method stub
		Company cmpny = comprepo.save(comp);
		if(cmpny!=null)
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Company "+comp.getComp_name()+" saved");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return cmpny;
		}
		else
		{
			return cmpny;
		}
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
		
		int res = comprepo.updateCompanyById(comp.getComp_name(), comp.getCompany_id());
		
		if(res>0){
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
