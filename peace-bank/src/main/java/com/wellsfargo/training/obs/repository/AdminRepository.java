package com.wellsfargo.training.obs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.obs.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	public Optional<Admin> findByAdminname(String name);
}
