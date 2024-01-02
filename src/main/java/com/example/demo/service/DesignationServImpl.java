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
//	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime today = LocalDateTime.now();  
	
	
	@Override
	public Designation saveDesignation(Designation desig) {
		// TODO Auto-generated method stub
		
		Designation des = desigrepo.save(desig);
		if(des!=null)
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+des.getDesig_name()+" is saved");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return des;
		}
		else {
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+desig.getDesig_name()+" is not saved");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return des;
		}
	}

	@Override
	public List<Designation> getAllDesignations() {
		// TODO Auto-generated method stub
		return desigrepo.findAll();
	}

	@Override
	public Designation getDesignationByid(String did) {
		// TODO Auto-generated method stub
		Long des_id = Long.parseLong(did);
		
		return desigrepo.findById(des_id).get();
	}

	@Override
	public int updateDesignation(Designation desig) {
		// TODO Auto-generated method stub
		int res = desigrepo.updateDesignationById(desig.getDesig_name(), desig.getDesig_id());
		if(res>0)
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+desig.getDesig_name()+" is updated");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res ;
		}
		else
		{
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Designation "+desig.getDesig_name()+" is not updated");
			act.setActivity_date(dtf.format(today));
			actserv.saveActivity(act);
			return res;
		}
	}

}
