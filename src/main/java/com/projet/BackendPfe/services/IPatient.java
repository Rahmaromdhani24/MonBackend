package com.projet.BackendPfe.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import com.projet.BackendPfe.Entity.Patient;

public interface IPatient {
	
	

public byte[] getImagePatient(long  id) throws Exception;

public void updateImagePatient(long id , MultipartFile file) throws IOException;

public void addPatient(Patient p);

public List<Patient> getAllPatient();

public void delete(long id);

public String generateUniqueUsername(String nom, String prenom);
public String generatePassword(int length , String nom , String prenom );

}