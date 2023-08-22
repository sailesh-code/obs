package com.wellsfargo.training.obs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.obs.model.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Long>{
	public List<Payee> findByFromAccount(long acc);
}
