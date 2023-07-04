package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Users;

@Controller
public class MainController {

	@Autowired
	BCryptPasswordEncoder passcode;
	
	@GetMapping("/")
	public String index()
	{
	//	System.err.println(passcode.encode("admin"));
		
		return "Home";
	}
	
	@GetMapping("changepass")
	public String changePassword()
	{
		return "ChangePassword";
	}
	
	@PostMapping("changepassword")@ResponseBody
	public String updatePassword(@ModelAttribute("Users")Users users,RedirectAttributes attr)
	{
		return ""+users.getUser_pass();
	}
	
}
