package com.projet.BackendPfe.Controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.projet.BackendPfe.EmailSenderService;
import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.UserDetailsImpl;
import com.projet.BackendPfe.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired 	UserRepository repository;
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserRepository userRepository;
	@Autowired	AdminDigitalMangerRepository adminRepository;
	@Autowired  EmailSenderService senderService;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	@Autowired	UserDetailsServiceImpl servicee;
	
	public static String nom;
	public static String prenom;
	public static String password;
	public static String username;
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
		System.out.println("aaaa");
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword())
			
				);
		  System.out.println("bbbbb");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
				userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail()
												
											));
	}
	
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") long id) {
		User user = userRepository.findById(id).get() ; 
		return user ; 
	}
/********************************************************************************************************/
	@PostMapping("/sendEmailAdminEtMedecin")
	public ResponseEntity<?> sendEmailMedecin(@RequestParam("email") String email)throws Exception {
		
		if(repository.existsByEmail(email) == false) {
		return ResponseEntity
					.status(500)
					.body(new Message("Email n'existe pas !!!!!"));
		}
			
		else {
	User user = repository.findByEmail(email);
	nom=user.getNom();
	prenom=user.getPrenom();
	password=user.getReservePassword();
	username=user.getUsername();
	senderService.sendEmailWithImagesAndButtonMedecin(email, "rahmaromdhani08@gmail.com",
			"Objet : Bienvenue à notre Application Clinique NAR!",EmailSenderService.getReserveBody());
	        System.out.println("Email Wsellll  !!!");
			return ResponseEntity.ok(new Message("Email send !!!!"));
			
		}
	}
	/*************************************************************************************************/
		@PostMapping("/sendEmailPatient")
		public ResponseEntity<?> sendEmailPatient(@RequestParam("email") String email)throws Exception {
			
			if(repository.existsByEmail(email) == false) {
			return ResponseEntity
						.status(500)
						.body(new Message("Email n'existe pas !!!!!"));
			}
				
			else {
		User user = repository.findByEmail(email);
		nom=user.getNom();
		prenom=user.getPrenom();
		password=user.getReservePassword();
		username=user.getUsername();
		senderService.sendEmailWithImagesAndButtonPatient(email, "rahmaromdhani08@gmail.com",
				"Objet: Bienvenue à la Clinique NAR",EmailSenderService.getReserveBody());
		        System.out.println("Email Wsellll  !!!");
				return ResponseEntity.ok(new Message("Email send !!!!"));
				
			}
	}
	
	    /****************** Test put ***********
	    @PutMapping( "/updateEx/{id}")
		 public ResponseEntity<Message> saveUser (@PathVariable ("id") long id , @RequestParam("file") MultipartFile file,
				 @RequestParam("expert") String expert) throws JsonParseException , JsonMappingException , Exception
		 {
	    	
	       Expert expert1 = expertRepository.findById(id).get();
	        expert1 =new ObjectMapper().readValue(expert, Expert.class); 
	        expert1.setImage(expertService.compressZLib(file.getBytes()));
	        Expert  resultat  = repository.save(expert1);
	        	return new ResponseEntity<Message>(new Message ("Expert  savrd "),HttpStatus.OK);	
		 }
*/

	
    public static void setNom(String nomm) {
        nom = nomm;
    }

    public static String getNom() {
        return nom;
    }
    public static void setPrenom(String nomm) {
        prenom = nomm;
    }

    public static String getPrenom() {
        return prenom;
    }
    public static void setPassword(String nomm) {
        password = nomm;
    }

    public static String getPassword() {
        return password;
    }
    public static void setUsername(String nomm) {
        username = nomm;
    }

    public static String getUsername() {
        return username;
    }
}
		  
