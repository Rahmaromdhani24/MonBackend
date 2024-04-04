package com.projet.BackendPfe.Controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.projet.BackendPfe.EmailSenderService;
import com.projet.BackendPfe.Entity.Patient;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.PatientRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.PatientService;
import com.projet.BackendPfe.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	PatientRepository repository;
	@Autowired	private  PatientService service  ;
	@Autowired  EmailSenderService senderService;
	@Autowired	AdminMedicalManagerRepository repositoryAdmin;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	@Autowired	UserRepository repuser;


	/***********************************************************************************************/
	@PostMapping("/login")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest data) {
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
												 userDetails.getEmail()));}
											
	
	/***********************************************************************************************/	
	@PostMapping("/addPatientParAdminSansImage/{id}")
	public long addPatientParAdmin( @PathVariable("id") long  idAdmin , @RequestBody Patient patient ) throws IOException {
		if (repuser.existsByUsername(patient.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
		
		
		if (repuser.existsByEmail(patient.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (repuser.findByCin(patient.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}
		
		String motDePasse =service.generatePassword(8, patient.getPrenom(), patient.getNom());

		Patient user = new Patient( 
									patient.getCin() ,
									patient.getNom() ,
									patient.getPrenom() ,
									patient.getGender() ,
									service.generateUniqueUsername(patient.getPrenom(), patient.getNom()),
									patient.getEmail(),
									encoder.encode(motDePasse), 
									motDePasse ,
									patient.getTelephone()  ,
									patient.getImage(),
									new Date()
									, "Patient",
									repositoryAdmin.findById(idAdmin) ,
									patient.getDate_naissance(),
									patient.getAntecedants()) ;
				
                repository.save(user);
		return   repository.findByUsername(user.getUsername()).get().getId() ;
	}
	
	/************************************************************************************/
	  @PutMapping("/updatePatient/{id}")
	  public ResponseEntity<?> updateMedecin(@PathVariable("id") long id, @RequestBody Patient patient) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	    Optional<Patient> UtilisateurInfo = repository.findById(id);
	    Patient utilisateur = UtilisateurInfo.get();
	    if (repuser.existsByUsername(patient.getUsername())) {
	    	if(repuser.findById(id).get().getId() != (repuser.findByUsername(patient.getUsername()).get().getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (repuser.existsByEmail(patient.getEmail())) {
			if(repuser.findById(id).get().getId() != (repuser.findByEmail(patient.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));}}
		
		if (repuser.existsByCin(patient.getCin())) {
			if(repuser.findById(id).get().getId() != (repuser.findByCin(patient.getCin()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin is already in use!"));}}
		utilisateur.setCin(patient.getCin());
		utilisateur.setNom(patient.getNom());
		utilisateur.setPrenom(patient.getPrenom());
		utilisateur.setGender(patient.getGender());
		utilisateur.setUsername(patient.getUsername());
		utilisateur.setEmail(patient.getEmail());
		utilisateur.setTelephone(patient.getTelephone());  
		utilisateur.setPassword(encoder.encode(patient.getPassword()));
		utilisateur.setReservePassword(patient.getReservePassword());
		utilisateur.setDate_naissance(patient.getDate_naissance());
		utilisateur.setAntecedants(patient.getAntecedants());
	      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
	    } 
	  
	/***********************************************************************************************/	  
	
	   @PutMapping("/updateImageProfilePatient/{id}")
			public String updateImageProfile(@PathVariable("id") long id  , @RequestParam("file") MultipartFile file ) throws IOException {
					service.updateImagePatient(id,  file);
					
				return "Image profile Admin Medical Manager Updated  !!!!" ; }
	   

   /*************************************************************************************************/		
	
	    @GetMapping(path="getImageProfilePatient/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
	    public  byte[] getImagePatient(@PathVariable("id") long id) throws Exception{
	    	return 	service.getImagePatient(id);
	    }
   /**********************************************************************************************/
	    @GetMapping( "/getPatient/{id}" )
			public Patient getPatient(@PathVariable("id") long id) throws IOException {

			  Patient p = repository.findById(id).get();
				    return p;}
	  /***********************************************************************************************/
		@DeleteMapping("/deletPatient/{id}")
		public void deleteProduct(@PathVariable("id") long id){

			repository.deleteById(id);
		} 
		/*******************************************************************************************/
		@GetMapping("/all")
		public List<Patient> getAll(){
			return repository.findByRole("Patient");}
		
		/***********************************************************************************************/
		@GetMapping("/age/{idPatient}")
	     public int getAgePatient(@PathVariable("idPatient") long idPatient) {
		 
			 Patient patient = repository.findById(idPatient).get() ; 
			 // get date de NAissance de patient 
			Date date_naissance=patient.getDate_naissance();
			 // date d'aujourd'hui
		     LocalDate date = LocalDate.now() ; 
		     LocalDate localDate = date_naissance.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		     int yearDateNaissance  =localDate.getYear()  ;
		     int yearDateToDay = date.getYear()  ;
		     int agePatient = yearDateToDay-yearDateNaissance ; 
		  
	   	return agePatient ; } 
		
	/***********************************************************************************************/
		
		 @GetMapping("/homme")
		  public int getPatientsHomme() {
			 
				 List <Patient> patients = repository.findAll() ; 
			     List<Patient> resultat = new ArrayList<Patient>() ; 

				for(Patient patient :patients) {
			       if(patient.getGender().equals("homme")) {
		          	resultat.add(patient) ; 	}

				}
				int nbrPatient=resultat.size();
				return nbrPatient;
			 }
	/***********************************************************************************************/

		 @GetMapping("/femme")
		  public int getPatientsFemme() {
			 
				 List <Patient> patients = repository.findAll() ; 
			     List<Patient> resultat = new ArrayList<Patient>() ; 

				for(Patient patient :patients) {
			       if(patient.getGender().equals("femme")) {
		          	resultat.add(patient) ; 	}
}
				int nbrPatient=resultat.size();
				return nbrPatient;
			 }
		 
  /***********************************************************************************************/
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <Patient> patients = repository.findAll() ; 

				int nbrPatient=patients.size();
				return nbrPatient;
			 }
 }

