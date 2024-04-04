package com.projet.BackendPfe.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.AdminMedicalManager;
import com.projet.BackendPfe.Entity.User;

public interface IAdminMedicalManager {
	
	

public byte[] getImageAdminMedicalManager(long  id) throws Exception;
public void updateImageAdminMedicalManager(long id , MultipartFile file) throws IOException;
public String generateUniqueUsername(String nom, String prenom);
public String generatePassword(int length , String nom , String prenom );
}