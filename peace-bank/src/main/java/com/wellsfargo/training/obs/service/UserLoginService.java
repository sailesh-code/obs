package com.wellsfargo.training.obs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.repository.UserLoginRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserLoginService {
	@Autowired
	private UserLoginRepository userloginrepo;
	
	public UserLogin registerUser(UserLogin u) {
		return userloginrepo.save(u);
	}
	public Optional<UserLogin> loginUser(String username){
		return userloginrepo.findByUsername(username);
	}
}
