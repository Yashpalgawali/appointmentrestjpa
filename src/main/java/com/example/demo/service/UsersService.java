package com.example.demo.service;

import com.example.demo.model.Users;

public interface UsersService {

	public int updateUsersPassword(String pass,int id);
	
	public Users getUserByEmailId(String email);

}
