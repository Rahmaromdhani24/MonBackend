package com.projet.BackendPfe.services;


import java.util.List;
import com.projet.BackendPfe.Entity.DossierMedical;

public interface IDossierMedical {

	
	     DossierMedical ajouterDossier(DossierMedical dossierMedicale);
	     List<DossierMedical> getAll();
	     DossierMedical modifierDossier(long id ,DossierMedical dosssierMedicale);
	     String SupprimerDossier(long id);
	    DossierMedical getDossier(long id);

}
