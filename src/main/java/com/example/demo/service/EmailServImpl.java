package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.ActivityLogs;

@Service("emailserv")
public class EmailServImpl implements EmailService {

	private JavaMailSender mailsend;
	
	private ActivityService actserv;
	
	private Environment env; 
	
	public EmailServImpl(JavaMailSender mailsend, ActivityService actserv,Environment env) {
		super();
		this.mailsend = mailsend;
		this.actserv = actserv;
		this.env = env;
	}

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	 
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	  
	@Override
	public void sendSimpleEmail(String toemail, String body, String subject) {
		String from = env.getProperty("spring.mail.username");
		
		logger.info("Sending email to: {}, Subject: {}", toemail, subject);
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(toemail);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(body);
		System.err.println("Mail is "+message.toString());
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
