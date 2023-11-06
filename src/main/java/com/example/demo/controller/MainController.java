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
	
//	@GetMapping("/")
//	public String index(HttpSession sess) {
//		
//		String uname = (String) sess.getAttribute("username");
//		return (uname==null?  "Home" :  "redirect:/adminhome");
//	}
//	
//	@GetMapping("changepass")
//	public String changePassword(HttpSession sess) {
//		
//		Users user = userserv.getUserByUserName("admin");
//		sess.setAttribute("userid", user.getUser_id());
//		return "ChangePassword";
//	}
	
//	@PostMapping("changepassword")
//	public String updatePassword(@ModelAttribute("Users")Users users,HttpSession sess,RedirectAttributes attr) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String logged_user = auth.getName();
//		
//		String enpass = passcode.encode(users.getPassword());
//		int uid = (Integer) sess.getAttribute("userid");
//		int res = userserv.updateUsersPassword(enpass, uid);
//		
//		if(res>0) {
//			if(logged_user.equals("admin"))
//			{	
//				attr.addFlashAttribute("response", "Password updated successfully");
//				return "redirect:/adminhome";
//			}
//			else {
//				attr.addFlashAttribute("response", "Password updated successfully");
//				return "redirect:/";
//			}
//		}
//		else {
//			if(logged_user.equals("admin")) {
//				attr.addFlashAttribute("reserr", "Password is not updated ");
//				return "redirect:/adminhome";
//			}
//			else {
//				attr.addFlashAttribute("reserr", "Password is not updated ");
//				return "redirect:/";
//			}
//		}
//	}
	
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
