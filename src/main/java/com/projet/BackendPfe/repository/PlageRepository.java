package com.projet.BackendPfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.PlageHorraire;

public interface PlageRepository extends JpaRepository<PlageHorraire,Integer> {
}
