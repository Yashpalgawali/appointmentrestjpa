
package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Employee;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

@Controller
public class UserController {

	@Autowired
	UsersService userserv;
	
	@GetMapping("/forgotpass")
	public String forgotPassword()
	{
		return "ForgotPassword";
	}
	
	@PostMapping("/forgotpassword")@ResponseBody
	public String forGotPassword(@ModelAttribute("Users") Users users,RedirectAttributes attr)
	{
		Users user = userserv.getUserByEmailId(users.getUser_email());
		if(user!=null)
		{
			return "ConfirmOtpForgotPass";
		}
		else{
			attr.addFlashAttribute("reserr", "User Not found for Given Email");
			return "redirect:/";
		}
	}
	
	@GetMapping("/confotppass")@ResponseBody
	public String confOTPForgotPassword(@ModelAttribute("Users") Users user)
	{
		
		return "ConfirmOtpForgotPass";
	}
	
}
