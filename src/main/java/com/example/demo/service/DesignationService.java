package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Designation;

public interface DesignationService {

	public Designation saveDesignation(Designation desig);
	
	public List<Designation> getAllDesignations();
	
	public Designation getDesignationByid(String did);
	
	public int updateDesignation(Designation desig);
	
}
