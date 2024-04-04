package com.projet.BackendPfe.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.projet.BackendPfe.Entity.AdminMedicalManager;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminMedicalManagerService implements IAdminMedicalManager{

	@Autowired  AdminMedicalManagerRepository   repository ;
	
	
	@Override
	public byte[] getImageAdminMedicalManager(long id) throws Exception {
		String imageAdmin =repository.findById(id).getImage() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/Desktop/images projet web/",imageAdmin);
		return Files.readAllBytes(p);
	}

	@Override
	public void updateImageAdminMedicalManager(long id, MultipartFile file) throws IOException {
		 AdminMedicalManager  admin =repository.findById(id); 
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(System.getProperty("user.home")+"/Desktop/images projet web/",file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 admin.setImage(file.getOriginalFilename());
		 repository.save(admin);
		
	}

	@Override
	 public String generateUniqueUsername(String nom, String prenom) {
	        // Convertir le nom et le prénom en minuscules pour assurer la cohérence
	        nom = nom.toLowerCase();
	        prenom = prenom.toLowerCase();

	        // Concaténer le nom et le prénom pour former un nom d'utilisateur de base
	        String baseUsername = nom + "_" + prenom;

	        // Vérifier si le nom d'utilisateur est déjà pris
	        if (!(repository.existsByUsername(baseUsername))) {
	            // Si le nom d'utilisateur est disponible, le retourner tel quel
	            return baseUsername;
	        } else {
	            // Si le nom d'utilisateur est pris, ajouter des caractères aléatoires pour éviter les conflits
	            Random random = new Random();
	            StringBuilder uniqueUsernameBuilder = new StringBuilder(baseUsername);
	            // Ajouter des caractères aléatoires jusqu'à ce que le nom d'utilisateur soit unique
	            while (repository.existsByUsername(uniqueUsernameBuilder.toString())) {
	                char randomChar = (char) (random.nextInt(26) + 'a'); // Générer un caractère minuscule aléatoire
	                uniqueUsernameBuilder.append(randomChar);
	            }
	            return uniqueUsernameBuilder.toString();
	        }
	    }
	@Override 
	 public  String generatePassword(int length , String nom , String prenom ) {
	        List<Character> characters = new ArrayList<>();

	        // Ajouter les caractères du nom et du prénom à la liste
	        for (char c : (nom + prenom).toCharArray()) {
	            characters.add(c);
	        }

	        // Mélanger les caractères pour plus de variété
	        Random random = new Random();
	        StringBuilder password = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            if (!characters.isEmpty()) {
	                int randomIndex = random.nextInt(characters.size());
	                password.append(characters.remove(randomIndex));
	            } else {
	                // Si la liste est vide, réinitialiser en ajoutant à nouveau les caractères
	                for (char c : (nom + prenom).toCharArray()) {
	                    characters.add(c);
	                }
	            }
	        }

	        return password.toString();
	    }

}