package com.wellsfargo.training.obs.controller;

import org.apache.el.stream.Optional;
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
import com.wellsfargo.training.obs.dto.LoginResult;
import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;


@RestController
@RequestMapping(value = "/api")
public class UserLoginController {
	
	@Autowired
	public UserLoginService ulservice;
	private UserService uservice;
	private ObjectMapper objectMapper;
	
	public UserLoginController(UserLoginService userlservice , UserService uservice) {
		super();
		this.ulservice = userlservice;
		this.uservice = uservice;
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
	public LoginResult Login(@Validated @RequestBody UserLogin u) throws ResourceNotFoundException 
	{
		Boolean a = false;
		String UserName = u.getUsername();
		System.out.print(UserName);
		String Password = u.getPassword();
		
		UserLogin ul = ulservice.loginUser(UserName).orElseThrow(()->
		new ResourceNotFoundException("User not found for this id :: "));
		
		long accountnumber = ul.getUs().getAnumber();
		long id = ul.getUs().getId();
		
		if(UserName.equals(ul.getUsername()) && Password.equals(ul.getPassword())) {
			a = true;
		}
		LoginResult lr = new LoginResult();
		lr.setSuccess(a);
		lr.setAccountNumber(accountnumber);
		lr.setId(id);
		return lr;
}
//	@GetMapping("/login/{id}")
//	public 
}
