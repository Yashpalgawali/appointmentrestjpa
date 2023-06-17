package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LastUpdateTimeRepo;

@Service("lastupdatetimeserv")
public class LastUpdateTimeServImpl implements LastUpdateTimeService {

	@Autowired
	LastUpdateTimeRepo lastupdaterepo;
	
	@Override
	public int updateLastUpdateTime(String time) {
		// TODO Auto-generated method stub
		return lastupdaterepo.updateLastUpdateTime(time);
	}

}
