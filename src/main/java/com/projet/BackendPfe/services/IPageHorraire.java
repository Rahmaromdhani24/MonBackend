package com.projet.BackendPfe.services;

import java.util.List;
import com.projet.BackendPfe.Entity.PlageHorraire;

public interface IPageHorraire {

	PlageHorraire ajouterPlage(PlageHorraire plageHorraire);
    List<PlageHorraire> getAll();
    PlageHorraire modifierPlage(int id ,PlageHorraire plageHorraire);
    void SupprimerPlageHorraire(int id);
    PlageHorraire getPlage(int id);
}