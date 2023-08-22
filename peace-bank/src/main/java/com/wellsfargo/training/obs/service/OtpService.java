package com.wellsfargo.training.obs.service;

import java.util.*;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OtpService {

	private final Map<String, String> mp = new HashMap<>();
	
	public String generateOtp() {
		String otp = String.valueOf((int) (Math.random()*9000) + 1000);
		return otp;
	}
	
	public void storeOtp(String email , String otp) {
		mp.put(email, otp);
	}
	public String getOtp(String email) {
		return mp.get(email);
	}
	public void clearOtp(String email) {
		mp.remove(email);
	}
}
