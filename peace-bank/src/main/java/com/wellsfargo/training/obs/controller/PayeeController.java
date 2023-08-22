package com.wellsfargo.training.obs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.model.Payee;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.PayeeService;
import com.wellsfargo.training.obs.service.TransactService;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;

@RestController
@RequestMapping(value = "api/payee")
public class PayeeController {
	@Autowired
	private PayeeService pservice;
	private UserLoginService ulservice;
	private UserService uservice;
	private TransactService tservice;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> createPayee(@PathVariable(value = "id") long Aid , @RequestBody Payee p){
		UserLogin ul = new UserLogin();
		try {
			 ul = ulservice.findUser(Aid);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Error :" + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		ul = ulservice.findUser(Aid);
		long FromAccount = ul.getUs().getAbalance();
		Payee payee = new Payee();
		payee.setFromAccount(FromAccount);
		payee.setBenAccount(p.getBenAccount());
		payee.setBenName(p.getBenName());
		payee.setNickName(p.getNickName());
		
		pservice.registerPayee(payee);
		
		return  ResponseEntity.ok("Payee registered Successfully");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPayeeInfo(@PathVariable(value = "id") long Aid){
		try {
			List<Payee> p = pservice.showAllPayee(Aid);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Error :" + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		List<Payee> p = pservice.showAllPayee(Aid);
		return ResponseEntity.ok(p);
	}
}
