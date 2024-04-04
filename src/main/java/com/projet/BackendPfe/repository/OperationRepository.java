package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.Entity.Operation;
import com.projet.BackendPfe.Entity.RendezVous;
import com.projet.BackendPfe.Entity.Specialite;



@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer>{
	
	Operation  findById(int id);
	List<Operation> findAll();

}
