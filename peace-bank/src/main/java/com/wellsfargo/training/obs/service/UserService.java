package com.wellsfargo.training.obs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userrepo;
	
	public User registerUser(User u) {
		return userrepo.save(u);
	}
	public Optional<User> loginUser(String username){
		return userrepo.findByUsername(username);
	}
	public Optional<User> fetchUser(long a){
		return userrepo.findByAnumber(a);
	}
}
