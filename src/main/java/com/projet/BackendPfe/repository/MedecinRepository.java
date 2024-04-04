package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.Entity.Medecin;
import com.projet.BackendPfe.Entity.User;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long>{

	Medecin findByUsername(String username);
	Medecin findByEmail(String email);
	Boolean existsByUsername(String username);
    Optional<Medecin> findByImage(String image);
	Boolean existsByEmail(String email);
	List<Medecin> findByRole(String role);
	User   findByCin(int cin);


	
}
