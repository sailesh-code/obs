package com.wellsfargo.training.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.obs.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{

}
