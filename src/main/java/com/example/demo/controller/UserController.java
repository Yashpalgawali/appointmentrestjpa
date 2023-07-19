
package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Employee;
import com.example.demo.model.Users;
import com.example.demo.service.OtpService;
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
	
	@Autowired
	OtpService otpserv;
	
	@RequestMapping("/forgotpassword")
	public String forGotPassword(@ModelAttribute("Users") Users users,HttpSession sess ,RedirectAttributes attr)
	{
		Users user = userserv.getUserByEmailId(users.getUser_email());
		if(user!=null)
		{
			otpserv.generateotp(users.getUser_email());
			int otp = otpserv.getOtp(users.getUser_email());
			
			sess.setAttribute("vemail", users.getUser_email());
			sess.setAttribute("otp", otp);
			attr.addFlashAttribute("response","OTP sent to your email "+users.getUser_email());
			return "redirect:/confotppass";
		}
		else{
			attr.addFlashAttribute("reserr", "User Not found for Given Email");
			return "redirect:/forgotpass";
		}
	}
	
	@GetMapping("/confotppass")
	public String confOTPForgotPassword(@ModelAttribute("Users") Users users,Model model,HttpSession sess)
	{
		System.err.println("in side confotppass method page");
		model.addAttribute("vemail", sess.getAttribute("vemail"));
		return "ConfirmOtpForgotPass";
	}
	

	
	@PostMapping("/confotppassword")
	public String confirmOtpPassword(@ModelAttribute("Users") Users users, HttpSession sess,RedirectAttributes attr)
	{
		Integer n_otp = Integer.parseInt(users.getCnf_otp());
		int  new_otp = n_otp;
		Integer o_otp = (Integer) sess.getAttribute("otp");;
		int  old_otp = o_otp;
		
		System.err.println("Provided OTP = "+n_otp+" \n and OTP is session is = "+o_otp);
		if(new_otp==old_otp){
			Users user = userserv.getUserByEmailId(users.getUser_email());
			otpserv.clearOtp((String)sess.getAttribute("vemail"));
			sess.setAttribute("userid", user.getUser_id());
			return "redirect:/changepass";
		}
		else{
			attr.addFlashAttribute("reserr", "OTP did not matched");
			return "redirect:/confotppass";
		}
	}
	
}
