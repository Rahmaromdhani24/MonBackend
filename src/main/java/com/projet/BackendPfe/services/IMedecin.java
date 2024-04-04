package com.projet.BackendPfe.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IMedecin {
	
	
	
   public byte[] getImageMedecin(long id) throws Exception;
	
	public void updateImageMedecin(long id , MultipartFile file) throws IOException;
	
	public String generateUniqueUsername(String nom, String prenom);
}