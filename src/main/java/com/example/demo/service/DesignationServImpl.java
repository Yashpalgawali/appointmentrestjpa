package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ActivityLogs;
import com.example.demo.model.Designation;
import com.example.demo.repository.DesignationRepo;

@Service("desigserv")
public class DesignationServImpl implements DesignationService {

	@Autowired
	DesignationRepo desigrepo;
	
	@Autowired
	ActivityService actserv;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	LocalDateTime today ; 
	
	@Override
	public Designation saveDesignation(Designation desig) {
		Designation des = desigrepo.save(desig);
		if(des!=null) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+des.getDesig_name()+" is saved");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return des;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+desig.getDesig_name()+" is not saved");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return des;
		}
	}

	@Override
	public List<Designation> getAllDesignations() {
		return desigrepo.findAll();
	}

	@Override
	public Designation getDesignationByid(String did) {
		return desigrepo.findById(Long.parseLong(did)).get();
	}

	@Override
	public int updateDesignation(Designation desig) {
		int res = desigrepo.updateDesignationById(desig.getDesig_name(), desig.getDesig_id());
		if(res>0) {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+desig.getDesig_name()+" is updated");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res ;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+desig.getDesig_name()+" is not updated");
			today = LocalDateTime.now();
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res;
		}
	}
}