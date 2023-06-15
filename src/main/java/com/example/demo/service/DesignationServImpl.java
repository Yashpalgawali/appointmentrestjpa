package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Designation;
import com.example.demo.repository.DesignationRepo;

@Service("desigserv")
public class DesignationServImpl implements DesignationService {

	@Autowired
	DesignationRepo desigrepo;
	
	@Override
	public Designation saveDesignation(Designation desig) {
		// TODO Auto-generated method stub
		return desigrepo.save(desig);
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
		return desigrepo.updateDesignationById(desig.getDesig_name(), desig.getDesig_id());
	}

}
