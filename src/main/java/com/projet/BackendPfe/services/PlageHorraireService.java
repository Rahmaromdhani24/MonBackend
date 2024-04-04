package com.projet.BackendPfe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.BackendPfe.Entity.PlageHorraire;
import com.projet.BackendPfe.repository.PlageRepository;

@Service
public class PlageHorraireService implements IPageHorraire {

	@Autowired PlageRepository repository;
	
	@Override
	public PlageHorraire ajouterPlage(PlageHorraire plageHorraire) {
		// TODO Auto-generated method stub
		 return repository.save(plageHorraire);
	}

	@Override
	public List<PlageHorraire> getAll() {
		// TODO Auto-generated method stub
		  return repository.findAll();
	}

	@Override
	public PlageHorraire modifierPlage(int id, PlageHorraire plageHorraire) {
		// TODO Auto-generated method stub
		return repository.findById(id)
                .map(p -> {
                    p.setDate(plageHorraire.getDate());
                    p.setHeureDebut(plageHorraire.getHeureDebut());
                    p.setHeureFin(plageHorraire.getHeureFin());
                    p.setMedecin(plageHorraire.getMedecin());
                    return repository.save(p);
                }).orElseThrow(() -> new RuntimeException("plage Horraire non trové"));
    }

	@Override
	public void SupprimerPlageHorraire(int id) {
		// TODO Auto-generated method stub
		 repository.deleteById(id);}
	      

	@Override
	public PlageHorraire getPlage(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

}
