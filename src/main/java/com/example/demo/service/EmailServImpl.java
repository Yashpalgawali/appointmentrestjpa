package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.ActivityLogs;

@Service("emailserv")
public class EmailServImpl implements EmailService {

	@Autowired
	JavaMailSender mailsend;
	@Autowired
	ActivityService actserv;
	@Autowired
	Environment env;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	
	@Override
	public void sendSimpleEmail(String toemail, String body, String subject) {
		String from = env.getProperty("spring.mail.username");
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(toemail);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(body);
		try {
			mailsend.send(message);
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Email is sent to "+ toemail);
			act.setActivity_date(dtf.format(LocalDateTime.now()));
			actserv.saveActivity(act);
			
		}
		catch(Exception e)
		{
			System.err.println("mail sent failed");
			ActivityLogs act = new ActivityLogs();
			act.setActivity("Email is not sent to "+ toemail);
			act.setActivity_date(dtf.format(LocalDateTime.now()));
			actserv.saveActivity(act);
		}
		
	}

}
