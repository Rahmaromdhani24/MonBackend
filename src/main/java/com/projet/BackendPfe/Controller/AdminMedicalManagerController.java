package com.projet.BackendPfe.Controller;

import java.io.IOException;
import org.springframework.http.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.projet.BackendPfe.EmailSenderService;
import com.projet.BackendPfe.Entity.AdminMedicalManager;
import com.projet.BackendPfe.Entity.Medecin;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequestMedecin;
import com.projet.BackendPfe.services.AdminMedicalManagerService;
import com.projet.BackendPfe.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adminMedical")
public class AdminMedicalManagerController {

	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	AdminMedicalManagerRepository repository;
	@Autowired	private  AdminMedicalManagerService service  ;
	@Autowired  EmailSenderService senderService;
	@Autowired	AdminDigitalMangerRepository repositoryAdminD;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	@Autowired UserRepository userRepository;

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
	@PostMapping("/addAdminMedicalParAdminDigitalSansImage/{id}")
	public long addAdminMedicalParAdminDigitalSansImage( @PathVariable("id") long  idAdmin , @RequestBody AdminMedicalManager admin ) throws IOException {
		
		if (userRepository.existsByUsername(admin.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
		
		
		if (userRepository.existsByEmail(admin.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (userRepository.findByCin(admin.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}

		String motDePasse =service.generatePassword(8, admin.getPrenom(), admin.getNom());
		
		AdminMedicalManager user = new AdminMedicalManager( 
				admin.getCin(), 
				admin.getNom(), 
				admin.getPrenom(), 
				admin.getGender(), 
				service.generateUniqueUsername(admin.getPrenom(), admin.getNom()), 
				admin.getEmail(),
                encoder.encode(motDePasse),
                motDePasse,
                null, 
                new Date(),
                "Admin Medical Manager",
                repositoryAdminD.findById(idAdmin).get());
				
                repository.save(user);
		return   repository.findByUsername(user.getUsername()).getId() ;
	}
	/**************************************************************************************************/
	  @PutMapping("update/{id}")
	  public ResponseEntity<?> updateAdminMedicalManger(@PathVariable("id") long id, @RequestBody AdminMedicalManager Admin) throws IOException {
		    System.out.println("Update Utilisateur with ID = " + id + "...");
		   
		    Optional<AdminMedicalManager> UtilisateurInfo = Optional.ofNullable(repository.findById(id));
		    AdminMedicalManager utilisateur = UtilisateurInfo.get();
		    if (userRepository.existsByUsername(Admin.getUsername())) {
		    	if(repository.findById(id).getId() != (repository.findByUsername(Admin.getUsername()).getId())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Username is already taken!"));
			}
		    }
			if (userRepository.existsByEmail(Admin.getEmail())) {
				if(repository.findById(id).getId() != (repository.findByEmail(Admin.getEmail()).getId())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Email is already in use!"));
			}
			}
			  if (userRepository.existsByCin(Admin.getCin())) {
			    	if(userRepository.findById(id).get().getId() != (userRepository.findByCin(Admin.getCin()).getId())) {

					return ResponseEntity
							.badRequest()
							.body(new Message("Error: Cin of Digital Manager  is already in use!"));
				}
			  }
			

		    	utilisateur.setCin(Admin.getCin());
		    	utilisateur.setNom(Admin.getNom());
		    	utilisateur.setPrenom(Admin.getPrenom());
		    	utilisateur.setGender(Admin.getGender());
		    	utilisateur.setEmail(Admin.getEmail());
		    	utilisateur.setPassword(encoder.encode(Admin.getPassword()));
		    	utilisateur.setReservePassword(Admin.getReservePassword());
		    	
		      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);} 
		    
	  /***********************************************************************************************/	  
		@GetMapping("/getAdminMedical/{id}")
		public AdminMedicalManager get(@PathVariable("id") long  idAdmin) {
			
			AdminMedicalManager admin =  repository.findById(idAdmin);
			return admin;}
		
		/***********************************************************************************************/ 
	
	   @PutMapping("/updateImageProfileAdminMedical/{id}")
			public String updateImageProfile(@PathVariable("id") long id  , @RequestParam("file") MultipartFile file ) throws IOException {
					service.updateImageAdminMedicalManager(id,  file);
					
				return "Image profile Admin Medical Manager Updated  !!!!" ; }
	   
	   /***********************************************************************************************/
		
	   @GetMapping( path="/getImageProfileAdminMedical/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
		public byte[] getImage(@PathVariable("id") long id)throws Exception{
		  AdminMedicalManager admin = repository.findById(id);
         return  service.getImageAdminMedicalManager(admin.getId());}
			
	  /***********************************************************************************************/
		@DeleteMapping("/deletAdminMedical/{id}")
		public void deleteProduct(@PathVariable("id") long id){

			repository.deleteById(id);
		} 
		/*******************************************************************************************/
		@GetMapping("/all")
		public List<AdminMedicalManager> getAll(){
			return repository.findByRole("Admin Medical Manager");}
		
		
		/***********************************************************************************************/	
		
 }

