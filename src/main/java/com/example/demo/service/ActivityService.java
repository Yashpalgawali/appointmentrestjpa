package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ActivityLogs;

public interface ActivityService {

	public void saveActivity(ActivityLogs act);
	
	public List<ActivityLogs> getAllActivities();
	
}
