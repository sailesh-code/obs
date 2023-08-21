package com.wellsfargo.training.obs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.dto.BalanceUpdate;
import com.wellsfargo.training.obs.model.Transact;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.TransactService;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;

@RestController
@RequestMapping("/api/user")
public class TransactController {

	@Autowired
	private TransactService tservice;
	private UserLoginService ulservice;
	private UserService uservice;
	
	public TransactController(TransactService tservice , UserLoginService ulservice , UserService uservice) {
		this.tservice = tservice;
		this.ulservice = ulservice;
		this.uservice = uservice;
	}
	
	@PutMapping("/transaction/{id}")
	public ResponseEntity <?> createTransact(@PathVariable(value="id") long aNumber,@Validated @RequestBody Transact transact){
		
		long amount = transact.getAmount();
		long toaccount = transact.getToAcc();
		UserLogin u = ulservice.findUser(aNumber);
		long fromaccount = u.getUs().getAnumber();
		
		User fromUser = uservice.fetchUser(fromaccount);
		User toUser = uservice.fetchUser(toaccount);
		
		if(fromUser == null || toUser == null) {
			return ResponseEntity.notFound().build();
		}
		if(fromUser.getAbalance() < amount) {
			return ResponseEntity.badRequest().body("Insufficient Balance");
		}
		fromUser.setAbalance(fromUser.getAbalance() - amount);
		toUser.setAbalance(toUser.getAbalance() + amount);
		
		Transact t = new Transact();
		
		t.setAmount(amount);
		t.setBenName(transact.getBenName());
		t.setFromAcc(fromaccount);
		t.setToAcc(toaccount);
		t.setNickName(transact.getNickName());
		t.setRemarks(transact.getRemarks());
		t.setTranDate(transact.getTranDate());
		
		tservice.registerTransact(t);
		
		return ResponseEntity.ok("Transaction Successful");
	}
	
	@PutMapping("/{id}/updatebalance")
	public ResponseEntity<?> updateBalance(@PathVariable(value = "id") long Aid , @RequestBody BalanceUpdate request){
		UserLogin ul = ulservice.findUser(Aid);
		if(ul == null) {
			return ResponseEntity.notFound().build();
		}
		User u= ul.getUs();
		long amount = request.getAmount();
		if("deposit".equals(request.getAction())) {
			u.setAbalance(u.getAbalance() + amount);
		}
		else if("withdraw".equals(request.getAction())) {
			if(u.getAbalance() >= amount) {
				u.setAbalance(u.getAbalance() - amount);
			}
			else {
				return ResponseEntity.badRequest().body("Insufficient Balance");
			}
		}
		else {
			return ResponseEntity.badRequest().body("Invalid action");
		}
		
		uservice.registerUser(u);
		return ResponseEntity.ok("Balance updated successfully");
		
		
		
	}
}