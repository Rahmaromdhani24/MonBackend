package com.projet.BackendPfe.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.BackendPfe.Entity.DossierMedical;
import com.projet.BackendPfe.repository.DossierMedicalRepository;

@Service
public class DossierMedicalService  implements IDossierMedical{

	@Autowired DossierMedicalRepository repository ;

	@Override
	public DossierMedical ajouterDossier(DossierMedical dossierMedicale) {
		// TODO Auto-generated method stub
		 return repository.save(dossierMedicale);
	}

	@Override
	public List<DossierMedical> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public DossierMedical modifierDossier(long id, DossierMedical dossierMedicale) {
		// TODO Auto-generated method stub
		return repository.findById(id)
                .map(d -> {
                    d.setDate_creation_dossier(dossierMedicale.getDate_creation_dossier());
                    d.setDiagnostic(dossierMedicale.getDiagnostic());
                    d.setPatient(dossierMedicale.getPatient());
                    return repository.save(d);
                }).orElseThrow(() -> new RuntimeException("Dossier non trové"));
	}

	@Override
	public String  SupprimerDossier(long id) {
		// TODO Auto-generated method stub
		  repository.deleteById(id);
		  return "dossier Supprimée";}
			

	@Override
	public DossierMedical getDossier(long id) {
		// TODO Auto-generated method stub
		 return repository.findById(id).get();
	} 
	
	
}
