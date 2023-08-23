package com.wellsfargo.training.obs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.obs.model.User;
//import com.wellsfargo.training.obs.model.UserLogin;

public interface UserRepository extends JpaRepository<User, Long> {
//	@Query(
//		value = "SELECT * FROM UserLogin l WHERE l.username = ?1",
//		nativeQuery = true
//	)
//	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByAnumber(Long a);
//	@Query(value = "SELECT * from user_table u WHERE u.email = :email ",
//			nativeQuery = true)
	public Optional<User> findByEmail(String email);
}
