package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.Specialite;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.SpecialiteRepository;
import com.projet.BackendPfe.services.SpecialiteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/specialite")
public class SpecialiteController {

	
	  @Autowired SpecialiteRepository repository ; 
	  @Autowired private SpecialiteService service ; 
	
 /****************************************************************************************************/	
	  @PostMapping("/addSpecialite")
	  public ResponseEntity<?> ajouterSpecialie(@Valid @RequestBody Specialite specialite) {
	
			if (repository.existsByNom(specialite.getNom())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: cette Specialté existe deja !"));
			}
		  Specialite s = new Specialite(specialite.getNom(),new Date());
	  return new ResponseEntity<>(repository.save(s), HttpStatus.OK);} 
		 
  /***************************************************************************************************/	
	  @GetMapping( "/getSpecialite/{id}" )
		public Specialite getSpecialite(@PathVariable("id") int id)  {

		  Specialite specialite = repository.findById(id).get();
		  Specialite s = new Specialite(specialite.getId(), specialite.getNom(),specialite.getDate_creation());
				return s;
		}
	  /***********************************************************************************************/
		 @PutMapping("updateSpecialite/{id}")
		  public ResponseEntity<?> updateDepartement(@PathVariable("id") int id, @RequestBody Specialite departement) throws IOException {
				   
			    Specialite s = repository.findById(id).get();
			    if (repository.existsByNom(departement.getNom())) {
			    	if(repository.findById(id).get().getId() != (repository.findByNom(departement.getNom()).get().getId())) {
					return ResponseEntity
							.badRequest()
							.body(new Message("Error: Departemet deja existe!"));
				}
			    }else {
			 
			    	s.setNom(departement.getNom());}
			    	
			      return new ResponseEntity<>(repository.save(s), HttpStatus.OK);} 
		 

		
     /***************************************************************************************************/	
	  @GetMapping( "/getAll" )
		public List<Specialite> getAll()  {
		  List<Specialite> sp = new ArrayList<Specialite>();
		  sp = repository.findAll();
				return sp;
		}
	/***********************************************************************************************/
	  @DeleteMapping("/deletSpecialite/{id}")
		public void deleteSpecialite(@PathVariable("id") int id){

			repository.deleteById(id);
		} 
}
