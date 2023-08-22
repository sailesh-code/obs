package com.wellsfargo.training.obs.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.Payee;
import com.wellsfargo.training.obs.repository.PayeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PayeeService {
	@Autowired
	private PayeeRepository prepo;
	
	public Payee registerPayee(Payee p) {
		return prepo.save(p);
	}
	public List<Payee> showAllPayee(long acc){
		return prepo.findByFromAccount(acc);
	}
}
