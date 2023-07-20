package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

@Controller
public class MainController {

	@Autowired
	BCryptPasswordEncoder passcode;
	
	@Autowired
	UsersService userserv;
	
	@GetMapping("/")
	public String index(){
		return "Home";
	}
	
	@GetMapping("changepass")
	public String changePassword(){
		return "ChangePassword";
	}
	
	@PostMapping("changepassword")
	public String updatePassword(@ModelAttribute("Users")Users users,HttpSession sess,RedirectAttributes attr){
		String enpass = passcode.encode(users.getPassword());
		int uid = (Integer) sess.getAttribute("userid");
		int res = userserv.updateUsersPassword(enpass, uid);
		
		if(res>0)
		{
			attr.addFlashAttribute("response", "Password updated successfully");
			return "redirect:/";
		}
		else{
			attr.addFlashAttribute("reserr", "Password is not updated ");
			return "redirect:/";
		}
	}
	
}
