package com.wellsfargo.training.obs.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.Address;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;
//import com.wellsfargo.training.pms.model.Dealer;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	private UserService uservice;
	private UserLoginService ulservice;
	private ObjectMapper objectMapper;
	
	public UserController(UserService UserService , UserLoginService userlservice) {
		super();
		this.uservice = UserService;
		this. ulservice = userlservice;
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
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
//		UserLogin userlogin = user.getUserlogin();
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
	public ResponseEntity<String> Account( @RequestBody JsonNode jsonNode)throws JsonMappingException, JsonProcessingException{
		
		
		try {
			User u= uservice.fetchUser(jsonNode.get("anumber").asLong());
			UserLogin ul = objectMapper.treeToValue(jsonNode, UserLogin.class);
			ul.setUs(u);
			ulservice.registerUser(ul);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Error Message "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<>("User Created Succesfully \nUser Id is", HttpStatus.CREATED);
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
