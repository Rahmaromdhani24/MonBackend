package com.projet.BackendPfe.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projet.BackendPfe.Entity.DossierMedical;
import com.projet.BackendPfe.repository.DossierMedicalRepository;
import com.projet.BackendPfe.services.DossierMedicalService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {

	@Autowired DossierMedicalRepository  repository ; 
	@Autowired DossierMedicalService  service ; 
	
	 @PostMapping("/add")
	    public DossierMedical create(@RequestBody DossierMedical dosssierMedicale)
	    {
	        return service.ajouterDossier(dosssierMedicale);
	    }
	    @GetMapping("/all")
	    public List<DossierMedical> get(){
	        return service.getAll();
	    }
	    @PutMapping("update/{id}")
	    public DossierMedical update(@PathVariable int id ,@RequestBody DossierMedical dosssierMedicale){
	        return service.modifierDossier(id,dosssierMedicale);
	    }
	    @DeleteMapping("{id}")
	    public String delet(@PathVariable int id){
	        return service.SupprimerDossier(id);
	    }

	    @GetMapping("/get/{id}")
	    public DossierMedical getById(@PathVariable int id){
	        return service.getDossier(id);
	    }
}
