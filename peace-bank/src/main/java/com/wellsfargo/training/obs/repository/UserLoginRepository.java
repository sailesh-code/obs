package com.wellsfargo.training.obs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.obs.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{
	public Optional<UserLogin> findByUsername(String username);
	public Optional<UserLogin> findById(long id);
}
