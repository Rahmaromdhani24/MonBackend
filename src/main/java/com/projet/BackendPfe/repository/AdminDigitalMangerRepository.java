package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.AdminMedicalManager;
import com.projet.BackendPfe.Entity.Medecin;
import com.projet.BackendPfe.Entity.User;


	@Repository
	public interface AdminDigitalMangerRepository extends JpaRepository<AdminDigitalManager, Long>{
		Optional<AdminDigitalManager> findByUsername(String username);
		Boolean existsByUsername(String username);
		Boolean existsByEmail(String email);
		Boolean existsByCin(int cin);
		Optional<AdminDigitalManager> findByImage(String image);
		List<AdminDigitalManager> findByRole(String role);
		AdminDigitalManager  findByEmail(String email);
		User   findByCin(int cin);
	}


