package com.projet.BackendPfe.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.projet.BackendPfe.Entity.Medecin;
import com.projet.BackendPfe.repository.MedecinRepository;

@Service
public class MedecinService implements IMedecin {

	@Autowired
	MedecinRepository medecin;


	@Override
	public byte[] getImageMedecin(long id) throws Exception {
		String image =medecin.findById(id).get().getImage() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/Desktop/images projet web/",image);
		return Files.readAllBytes(p);
		
	}

	@Override
	public void updateImageMedecin(long id, MultipartFile file) throws IOException {
		 Medecin  generaliste = medecin.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(System.getProperty("user.home")+"/Desktop/images projet web/",file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 generaliste.setImage(file.getOriginalFilename());
		 
		 medecin.save(generaliste);
		
	}
	@Override
	 public String generateUniqueUsername(String nom, String prenom) {
	        // Convertir le nom et le prénom en minuscules pour assurer la cohérence
	        nom = nom.toLowerCase();
	        prenom = prenom.toLowerCase();

	        // Concaténer le nom et le prénom pour former un nom d'utilisateur de base
	        String baseUsername = nom + "_" + prenom;

	        // Vérifier si le nom d'utilisateur est déjà pris
	        if (!(medecin.existsByUsername(baseUsername))) {
	            // Si le nom d'utilisateur est disponible, le retourner tel quel
	            return baseUsername;
	        } else {
	            // Si le nom d'utilisateur est pris, ajouter des caractères aléatoires pour éviter les conflits
	            Random random = new Random();
	            StringBuilder uniqueUsernameBuilder = new StringBuilder(baseUsername);
	            // Ajouter des caractères aléatoires jusqu'à ce que le nom d'utilisateur soit unique
	            while (medecin.existsByUsername(uniqueUsernameBuilder.toString())) {
	                char randomChar = (char) (random.nextInt(26) + 'a'); // Générer un caractère minuscule aléatoire
	                uniqueUsernameBuilder.append(randomChar);
	            }
	            return uniqueUsernameBuilder.toString();
	        }
	    }
	    

}

