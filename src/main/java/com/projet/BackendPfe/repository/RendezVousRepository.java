package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.Entity.RendezVous;
import com.projet.BackendPfe.Entity.Specialite;



@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Integer>{
	
	RendezVous  findById(int id);
	List<RendezVous> findAll();

}
