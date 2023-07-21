package com.example.demo.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String changePassword(HttpSession sess){
		
		Users user = userserv.getUserByUserName("admin");
		sess.setAttribute("userid", user.getUser_id());
		return "ChangePassword";
	}
	
	@PostMapping("changepassword")
	public String updatePassword(@ModelAttribute("Users")Users users,HttpSession sess,RedirectAttributes attr){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String logged_user = auth.getName();
		
		String enpass = passcode.encode(users.getPassword());
		int uid = (Integer) sess.getAttribute("userid");
		int res = userserv.updateUsersPassword(enpass, uid);
		
		if(res>0)
		{
			if(logged_user.equals("admin"))
			{	
				attr.addFlashAttribute("response", "Password updated successfully");
				return "redirect:/addcompany";
			}
			else {
				attr.addFlashAttribute("response", "Password updated successfully");
				return "redirect:/";
			}
		}
		else{
			if(logged_user.equals("admin"))
			{
				attr.addFlashAttribute("reserr", "Password is not updated ");
				return "redirect:/addcompany";
			}
			else {
				attr.addFlashAttribute("reserr", "Password is not updated ");
				return "redirect:/";
			}
		}
	}
	
}
