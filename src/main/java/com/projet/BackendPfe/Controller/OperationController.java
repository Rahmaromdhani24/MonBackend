package com.projet.BackendPfe.Controller;

import java.io.IOException;
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
import com.projet.BackendPfe.Entity.Medecin;
import com.projet.BackendPfe.Entity.Operation;
import com.projet.BackendPfe.Entity.Patient;
import com.projet.BackendPfe.repository.MedecinRepository;
import com.projet.BackendPfe.repository.OperationRepository;
import com.projet.BackendPfe.repository.PatientRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/operation")

public class OperationController {


	@Autowired	OperationRepository repository; 
	@Autowired	PatientRepository repositoryP; 
	@Autowired	MedecinRepository repositoryM; 


	
   /***********************************************************************************************/
	 @PostMapping( "/addOperation/{idPatient}/{idMedecin}" )
		public ResponseEntity<?> addOperation(@PathVariable("idPatient") long idP , @PathVariable("idMedecin") long idM ,@RequestBody Operation operation)  {

		 Patient p = repositoryP.findById(idP).get();
		 Medecin m = repositoryM.findById(idM).get();
		 Operation op = new Operation(p , m ,operation.getDate_operation(),operation.getTypeOperation(), operation.getDescription_Operation(),  operation.getCause() );
		    return new ResponseEntity<>(repository.save(op), HttpStatus.OK);
		}
	/***********************************************************************************************/		    
	
	 @GetMapping( "/getOperation/{id}" )
		public Operation getAdmin(@PathVariable("id") int id)  {

	
				return repository.findById(id);
		}
	/***********************************************************************************************/
	 @PutMapping("update/{id}")
	  public ResponseEntity<?> updateOperation(@PathVariable("id") int id, @RequestBody Operation operation)  {
		
		   

		   Operation op = repository.findById(id);
		   
	       op.setMedecin(operation.getMedecin());
	       op.setTypeOperation(operation.getTypeOperation());	
	       op.setDescription_Operation(operation.getDescription_Operation());	
	       op.setCause(operation.getCause());	
		    	
		      return new ResponseEntity<>(repository.save(op), HttpStatus.OK);} 

    /*************************************************************************************************/		

	  @DeleteMapping("/deletOperation/{id}")
	  public String  deleteExpert(@PathVariable("id") int id )
	  {
		  repository.deleteById(id);
		  return "Supprimer avec succées!!!!" ;}
	  
/*******************************************************************************************/
}
