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
		return userrepo.updateUsersPassword(pass, id);
	}

	@Override
	public Users getUserByEmailId(String email) {
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
		return userrepo.findByUsername(uname);
	}
}
