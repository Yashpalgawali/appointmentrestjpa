package com.example.demo.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	public JavaMailSender mailSender()
	{
		JavaMailSenderImpl mailsend = new JavaMailSenderImpl();
		 
		mailsend.setHost("smtp.gmail.com");
		mailsend.setPort(587);
		mailsend.setUsername("gawaliyashpal@gmail.com");
		mailsend.setPassword("qjdrosuymhjbuodb");
		
		Properties props = mailsend.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.debug", "false");
    	
		return mailsend;
	}
	
	
}
