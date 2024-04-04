package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequestMedecin;
import com.projet.BackendPfe.services.AdminDigitalManagerService;
import com.projet.BackendPfe.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adminDigital")

public class AdminDigitalManagerController {

	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	AdminDigitalMangerRepository repository;
	@Autowired	UserRepository userRepository; 
	@Autowired	private  AdminDigitalManagerService service  ;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	
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
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequestMedecin signUpRequest)  {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));	}
		
		if (repository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));
		}
		if (repository.findByCin(signUpRequest.getCin())!= null) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin of Digital Manager  is already in use!"));
		}
		AdminDigitalManager admin = new AdminDigitalManager(    signUpRequest.getCin(),
																signUpRequest.getNom(), 
																signUpRequest.getPrenom(),
																signUpRequest.getGender(), 
																signUpRequest.getUsername(), 
																signUpRequest.getEmail(),
																encoder.encode(signUpRequest.getPassword()),
																signUpRequest.getReservePassword(),
																null
																,new  Date() ,
																"Admin Digital Manager");
		repository.save(admin);

		return ResponseEntity.ok(new Message("Admin  registered successfully!"));}	
	/***********************************************************************************************/		    
	
	 @GetMapping( "/getAdminDigital/{id}" )
		public AdminDigitalManager getAdmin(@PathVariable("id") long id)  {

		  AdminDigitalManager admin = repository.findById(id).get();
				return admin;
		}
	/***********************************************************************************************/
	 @PutMapping("update/{id}")
	  public ResponseEntity<?> updateAdminDigitalManger(@PathVariable("id") long id, @RequestBody AdminDigitalManager Admin) throws IOException {
		
		 System.out.println("Update Utilisateur with ID = " + id + "...");
		   

		    Optional<AdminDigitalManager> UtilisateurInfo = repository.findById(id);
		    AdminDigitalManager utilisateur = UtilisateurInfo.get();
		    if (userRepository.existsByUsername(Admin.getUsername())) {
		    	if(userRepository.findById(id).get().getId() != (userRepository.findByUsername(Admin.getUsername()).get().getId())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Username is already taken!"));
			}
		    }
		  if (userRepository.existsByEmail(Admin.getEmail()) && (userRepository.findById(id).get().getId() != (repository.findByEmail(Admin.getEmail()).getId()))  ) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Email is already in use!"));}
			
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
		    	utilisateur.setUsername(Admin.getUsername());
		    	utilisateur.setEmail(Admin.getEmail());
		    	utilisateur.setPassword(encoder.encode(Admin.getPassword()));
		    	utilisateur.setReservePassword(Admin.getReservePassword());
		    	
		      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);} 
	 

	/***********************************************************************************************/
	 @PutMapping("/updateImageProfileAdminDigial/{id}")
		public String updateImageProfile(@PathVariable("id") long id  , @RequestParam("file") MultipartFile file ) throws IOException {
				service.updateImageAdminDigitalManager(id,  file);
				
			return "Image profile Admin Digial Manager Updated  !!!!" ; 
		}
	 /***********************************************************************************************/
    @GetMapping( path="/getImageProfileAdminDigial/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
		public byte[] getImage(@PathVariable("id") long id)throws Exception{
		  AdminDigitalManager admin = repository.findById(id).get();
	  return  service.getImageAdminDigitalManager(admin.getId());
			
	}
    /*************************************************************************************************/		

	  @DeleteMapping("/deletAdminDigital/{id}")
	  public String  deleteExpert(@PathVariable("id") long id )
	  {
		  repository.deleteById(id);
		  return "Supprimer avec succées!!!!" ;}
	  
/*******************************************************************************************/
}
