package com.wellsfargo.training.obs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.Admin;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService aservice;
	@PostMapping("/login")
	public Boolean adminLogin(@Validated @RequestBody Admin admin) throws ResourceNotFoundException
	{
		Boolean b = false;
		String username = admin.getAdminname();
		String password = admin.getApassword();
		
		Admin a = aservice.loginAdmin(username).orElseThrow(()->
		new ResourceNotFoundException("User not found for this id :: "));
		
		if(username.equals(a.getAdminname()) && password.equals(a.getApassword())) {
			b = true;
		}
		return b;
		
	}
}
