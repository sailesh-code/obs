package com.wellsfargo.training.obs.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wellsfargo.training.obs.model.Address;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.UserService;
import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
//import com.wellsfargo.training.pms.model.Dealer;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	private UserService uservice;
	
	public static long generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> createUser(@Validated @RequestBody  User user){
		Address address = user.getAddress();
		UserLogin userlogin = user.getUserlogin();
		user.setAddress(address);
		address.setUser(user);
		//userlogin.setUs(user);
		//user.setUserlogin(userlogin);
		user.setAnumber(generateRandom(12));
		user.setAbalance(0);
		
		User registeruser = uservice.registerUser(user);
		
		if(registeruser != null) {
			return ResponseEntity.ok("Registration Successfull");
		}
		else {
			return ResponseEntity.badRequest().body("Registration Failed");
		}
	}
	
	@PostMapping("/account")
	public ResponseEntity<String> Account(@Validated @RequestBody User user)throws ResourceNotFoundException{
		UserLogin ul = user.getUserlogin();
		
		User u = uservice.fetchUser(user.getAnumber()).orElseThrow(()->
		new ResourceNotFoundException("User not found for this id :: "));
		u.setUserlogin(ul);
//		ul.setUs(u);
		return ResponseEntity.ok("Online registration Successfull");
	}
	
	@PostMapping("/login")
	public Boolean Login(@Validated @RequestBody User user) throws ResourceNotFoundException 
	{
		Boolean a = false;
		String UserName = user.getUserlogin().getUsername();
		System.out.print(UserName);
		String Password = user.getUserlogin().getPassword();
		
		User u = uservice.loginUser(UserName).orElseThrow(()->
		new ResourceNotFoundException("User not found for this id :: "));
		
		if(UserName.equals(u.getUserlogin().getUsername()) && Password.equals(u.getUserlogin().getPassword())) {
			a = true;
		}
		return a;
	}
	
}
