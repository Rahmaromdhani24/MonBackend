package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.BackendPfe.Entity.Specialite;



@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Integer>{
	
	Optional<Specialite> findById(int id);
	Optional<Specialite> findByNom(String nom);
	Boolean existsByNom(String nom);
	List<Specialite> findAll();

}
