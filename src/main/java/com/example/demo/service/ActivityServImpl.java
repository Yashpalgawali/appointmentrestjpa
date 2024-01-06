package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ActivityLogs;
import com.example.demo.repository.ActivityRepo;

@Service("actserv")
public class ActivityServImpl implements ActivityService {

	@Autowired
	ActivityRepo actrepo;
	
	@Override
	public void saveActivity(ActivityLogs act) {
		actrepo.save(act);
	}

	@Override
	public List<ActivityLogs> getAllActivities() {
		return actrepo.findAll();
	}

}
