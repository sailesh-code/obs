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

	@Autowired
	private final UserLoginRepository userLoginRepository;

    	public UserService(UserLoginRepository userLoginRepository) {
        	this.userLoginRepository = userLoginRepository;
    	}
	
	public User registerUser(User u) {
		return userrepo.save(u);
	}
//	public Optional<User> loginUser(String username){
//		return userrepo.findByUsername(username);
//	}
	public User fetchUser(long a){
		Optional<User> u = userrepo.findByAnumber(a);
		return u.get();
	}
	public User fetchUserByEmail(String email) {
		Optional<User> u = userrepo.findByEmail(email);
		return u.get();
	}
	public void updatePassword(User user, String newPassword) {
        UserLogin userLogin = user.getUserlogin();

        if (userLogin != null) {
            userLogin.setPassword(newPassword);
            userLoginRepository.save(userLogin);
        }
    }
}
