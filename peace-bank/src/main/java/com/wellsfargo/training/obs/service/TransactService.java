package com.wellsfargo.training.obs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.Transact;
import com.wellsfargo.training.obs.repository.TransactRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactService {
	@Autowired
	private TransactRepository transactrepo;
	
	public Transact registerTransact( Transact t) {
		return transactrepo.save(t);
	}
	public List<Transact> showTransact(long AccNo){
		return transactrepo.findByFromAccOrToAcc(AccNo, AccNo);
	}
}
