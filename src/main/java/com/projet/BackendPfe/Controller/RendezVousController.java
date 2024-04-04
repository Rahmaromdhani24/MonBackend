package com.projet.BackendPfe.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projet.BackendPfe.Entity.RendezVous;
import com.projet.BackendPfe.Entity.Specialite;
import com.projet.BackendPfe.repository.RendezVousRepository;
import com.projet.BackendPfe.services.RendezVousService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rendezVous")
public class RendezVousController {

	
	  @Autowired RendezVousRepository repository ; 
	  @Autowired private RendezVousService service ; 
	
 /****************************************************************************************************/	
	  @PostMapping("/addSpecialite")
	  public ResponseEntity<?> ajouterSpecialie(@Valid @RequestBody RendezVous specialite) {
	
		/*	if (repository.existsByNom(specialite.getNom())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: cette Specialté existe deja !"));
			}*/
			RendezVous s = new RendezVous();
	  return new ResponseEntity<>(repository.save(s), HttpStatus.OK);} 
		 
  /***************************************************************************************************/	
	 /* @GetMapping( "/getRendezVous/{id}" )
		public RendezVous getSpecialite(@PathVariable("id") int id)  {

		 // Specialite specialite = repository.findById(id);
		 // RendezVous s = new RendezVous(specialite.getId(), specialite.getNom(),specialite.getDate_creation());
				return s;
		}*/
	/***********************************************************************************************/
	 @PutMapping("/updateImageSpecialite/{id}")
		
	 /***********************************************************************************************/
   //  @GetMapping( path="/getImageSpecialite/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
		
     /***************************************************************************************************/	
	  @GetMapping( "/getAll" )
		public List<RendezVous> getAll()  {
		  List<RendezVous> rv = new ArrayList<RendezVous>();
		  rv = repository.findAll();
				return rv;
		}
	/***********************************************************************************************/
	  @DeleteMapping("/deletRendezVous/{id}")
		public void deleteSpecialite(@PathVariable("id") int id){

			repository.deleteById(id);
		} 
}
