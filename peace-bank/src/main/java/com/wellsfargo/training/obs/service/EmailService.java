package com.wellsfargo.training.obs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendSimpleEmail(String to, String subject , String body) {
		SimpleMailMessage message = new SimpleMailMessage();
	}
}
