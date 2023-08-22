package com.wellsfargo.training.obs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.service.EmailService;
import com.wellsfargo.training.obs.service.OtpService;
import com.wellsfargo.training.obs.service.UserService;

@RestController
@RequestMapping("api/forgot-password")
public class ForgotPasswordController {
	@Autowired
	private UserService uservice;
	private OtpService otpService;
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<?> sendOtpToEmail(@RequestBody String email){
		try {
			User u = uservice.fetchUserByEmail(email);
		}
		catch(Exception e) {
//			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Error : " + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		User u = uservice.fetchUserByEmail(email);
		
		String otp = otpService.generateOtp();
		System.out.println(otp);
		otpService.storeOtp(email, otp);
		
		String subject = "Forgot Password OTP";
		String body = "Your OTP is" + otp;
		
		emailService.sendOtpEmail(email, subject, body);
		
		return ResponseEntity.ok("OTP sent to mail");
	}
	
}
