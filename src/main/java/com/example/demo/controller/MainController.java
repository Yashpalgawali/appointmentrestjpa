package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class MainController {

	@Autowired
	BCryptPasswordEncoder passcode;
	
	@Autowired
	UsersService userserv;

	@PutMapping("/changepassword")
	public ResponseEntity<Users> updatePassword(@RequestBody Users users) {
		String enpass = passcode.encode(users.getCnf_pass());
		int uid = (Integer) users.getUser_id();
		int res = userserv.updateUsersPassword(enpass, uid);
		if(res > 0) {
			return new ResponseEntity<Users>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Users>(HttpStatus.OK);
		}	
	}
}