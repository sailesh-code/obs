package com.wellsfargo.training.obs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.obs.model.Transact;

public interface TransactRepository extends JpaRepository<Transact, Long>{
	public List<Transact> findByFromAccOrToAcc(long FromAcc , long ToAcc);
}
