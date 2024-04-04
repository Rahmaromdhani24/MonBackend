package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.projet.BackendPfe.Entity.Medecin;
import com.projet.BackendPfe.Entity.Patient;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.MedecinRepository;
import com.projet.BackendPfe.repository.SpecialiteRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.AdminMedicalManagerService;
import com.projet.BackendPfe.services.MedecinService;
import com.projet.BackendPfe.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/medecin")
public class MedecinController {
	
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	MedecinRepository expertRepository;
	@Autowired	AdminMedicalManagerRepository adminrepository;
	@Autowired	UserRepository userRepository;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	@Autowired	SpecialiteRepository specialiteRepository;
	@Autowired	private MedecinService medecinService ;
	@Autowired	private AdminMedicalManagerService service ;
/**************************************************************************************************/	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword()));	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail()
											));}
	
	/**********************************************************************************/
	@PostMapping("/addMedecinParAdminSansImage/{idAdmin}/{idSpecialite}")
	public long addMedecinParAdmin( @PathVariable("idAdmin") long  idAdmin ,@PathVariable("idSpecialite") int  idSpecialite , @RequestBody Medecin medecin ) throws IOException {
		if (userRepository.existsByUsername(medecin.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
	
		if (userRepository.existsByEmail(medecin.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (userRepository.findByCin(medecin.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}
		
		String motDePasse =service.generatePassword(8, medecin.getPrenom(), medecin.getNom());

		Medecin user = new Medecin( specialiteRepository.findById(idSpecialite).get(),
				medecin.getCin() ,
				medecin.getNom() ,
				medecin.getPrenom() ,
				medecinService.generateUniqueUsername(medecin.getPrenom(), medecin.getNom()),
				medecin.getEmail(),
				encoder.encode(motDePasse),
				motDePasse ,
				medecin.getGender() , 
				medecin.getTelephone()  ,
				medecin.getImage(),
				new Date()
				, "Medecin",
				adminrepository.findById(idAdmin)   ) ;
				
                expertRepository.save(user);
		return   expertRepository.findByUsername(user.getUsername()).getId() ;
	}
	/************************************************************************************/
	  @PutMapping("/updateMedecin/{id}")
	  public ResponseEntity<?> updateMedecin(@PathVariable("id") long id, @RequestBody Medecin medecin) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	    Optional<Medecin> UtilisateurInfo = expertRepository.findById(id);
	    Medecin utilisateur = UtilisateurInfo.get();
	    if (userRepository.existsByUsername(medecin.getUsername())) {
	    	if(expertRepository.findById(id).get().getId() != (userRepository.findByUsername(medecin.getUsername()).get().getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (userRepository.existsByEmail(medecin.getEmail())) {
			if(userRepository.findById(id).get().getId() != (userRepository.findByEmail(medecin.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));}}
		
		if (userRepository.existsByCin(medecin.getCin())) {
			if(userRepository.findById(id).get().getId() != (userRepository.findByCin(medecin.getCin()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin is already in use!"));}}
		utilisateur.setSpecialite(medecin.getSpecialite());
		utilisateur.setCin(medecin.getCin());
    	utilisateur.setNom(medecin.getNom());
    	utilisateur.setPrenom(medecin.getPrenom());
    	utilisateur.setGender(medecin.getGender());
    	utilisateur.setUsername(medecin.getUsername());
    	utilisateur.setEmail(medecin.getEmail());
    	utilisateur.setTelephone(medecin.getTelephone());  
    	utilisateur.setPassword(encoder.encode(medecin.getPassword()));
    	utilisateur.setReservePassword(medecin.getReservePassword());
	    	
	      return new ResponseEntity<>(expertRepository.save(utilisateur), HttpStatus.OK);
	    } 
	  
/**************************************************************************************************/
	  @GetMapping( "/getMedecin/{id}" )
		public Medecin getExpert(@PathVariable("id") long id) throws IOException {

		  Medecin expert = expertRepository.findById(id).get();
			    return expert;}

/*************************************************************************************************/		
	
	    @GetMapping(path="getImageMedecin/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
	    public  byte[] getImageExpert(@PathVariable("id") long id) throws Exception{
	    	return medecinService.getImageMedecin(id);
	    }
/*************************************************************************************************/		
	    @PutMapping("/updateImageMedecin/{id}")
		public String updateImage1DD(@PathVariable("id") long id  , @RequestParam("file") MultipartFile file ) throws IOException {
	    	medecinService.updateImageMedecin(id,  file);
				
			return "Image de medecin modifié" ; }
	    
/*************************************************************************************************/		

	  @DeleteMapping("/delecteMedecin/{id}")
	  public void deleteExpert(@PathVariable("id") long id )
	  {
		  expertRepository.deleteById(id);}
	  
/*******************************************************************************************/
		@GetMapping("/all")
		public List<Medecin> getAll(){
			return expertRepository.findByRole("Medecin");}
		
		
/******************************************************************************************/
		@GetMapping("/patientsMedecin/{idMedecin}")
        public List<Patient> getAllPatientsDr(@PathVariable("idExpert") long idMedecin){
		
		
	    	List<Patient> res= new ArrayList<>() ; 
	    	
	
	    	return res ; }
	    	
/*********************************************************************************************/
		 @GetMapping("/homme")
		  public int getMedecinsHomme() {
			 
				 List <Medecin> patients = expertRepository.findAll() ; 
			     List<Medecin> resultat = new ArrayList<Medecin>() ; 

				for(Medecin patient :patients) {
			       if(patient.getGender().equals("homme")) {
		          	resultat.add(patient) ; 	}

				}
				int nbrPatient=resultat.size();
				return nbrPatient;
			 }
	/***********************************************************************************************/

		 @GetMapping("/femme")
		  public int getMedecinsFemme() {
			 
				 List <Medecin> patients = expertRepository.findAll() ; 
			     List<Medecin> resultat = new ArrayList<Medecin>() ; 

				for(Medecin patient :patients) {
			       if(patient.getGender().equals("femme")) {
		          	resultat.add(patient) ; 	}

				}
				int nbrPatient=resultat.size();
				return nbrPatient;
			 }
		 
 /***********************************************************************************************/
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <Medecin> patients = expertRepository.findAll() ; 

				int nbrPatient=patients.size();
				return nbrPatient;
			 }
 }

