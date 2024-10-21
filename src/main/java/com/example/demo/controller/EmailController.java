package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {

	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	Environment env;
	
	@GetMapping("/")
	public String testEmail() {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("crankyash@gmail.com");
		mail.setText("This is a test mail ");
		mail.setFrom(env.getProperty("${spring.mail.username}"));
		mail.setSubject("Test mail");
		
		try {
			mailsender.send(mail);
		}
		catch (MailSendException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "";
	}
}
