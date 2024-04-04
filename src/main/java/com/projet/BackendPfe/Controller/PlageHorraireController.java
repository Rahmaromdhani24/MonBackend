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
import com.projet.BackendPfe.Entity.PlageHorraire;
import com.projet.BackendPfe.repository.PlageRepository;
import com.projet.BackendPfe.services.PlageHorraireService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/plageHorraire")
public class PlageHorraireController {
	
	@Autowired	private  PlageHorraireService service  ;
	@Autowired	PlageRepository repository;

	
	   @PostMapping("/add")
	    public PlageHorraire create(@RequestBody PlageHorraire plageHorraire)
	    {
	        return service.ajouterPlage(plageHorraire);
	    }
	    @GetMapping("/all")
	    public List<PlageHorraire> get(){
	        return service.getAll();
	    }
	    @PutMapping("update/{id}")
	    public PlageHorraire update(@PathVariable int id ,@RequestBody PlageHorraire plageHorraire){
	        return service.modifierPlage(id, plageHorraire);
	    }
	    @DeleteMapping("{id}")
	    public void delet(@PathVariable int id){
	         service.SupprimerPlageHorraire(id);
	    }
	    @GetMapping("/get/{id}")
	    public PlageHorraire getPlage(@PathVariable int id){
	        return service.getPlage(id);
	    }

}
