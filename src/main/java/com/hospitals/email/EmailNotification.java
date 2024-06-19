package com.hospitals.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailNotification {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void mailSender(String subject, String text, String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("anilprajapati80814895@gmail.com");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true); 
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		
		}
	}

}
