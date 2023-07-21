package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

@Service("userserv")
public class UsersServImpl implements UsersService {

	@Autowired
	UsersRepository userrepo;
	
	@Override
	public int updateUsersPassword(String pass, int id) {
		// TODO Auto-generated method stub
		return userrepo.updateUsersPassword(pass, id);
	}

	@Override
	public Users getUserByEmailId(String email) {
		// TODO Auto-generated method stub
		
		Users user = userrepo.getUserByEmailId(email); 
		try {
			return user;
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public Users getUserByUserName(String uname) {
		// TODO Auto-generated method stub
		return userrepo.findByUsername(uname);
	}

}
