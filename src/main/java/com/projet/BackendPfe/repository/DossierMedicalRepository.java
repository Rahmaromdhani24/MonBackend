package com.projet.BackendPfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.Entity.DossierMedical;


@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long>{

	
}
