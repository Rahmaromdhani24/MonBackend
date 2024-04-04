package com.projet.BackendPfe.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.User;
public interface IAdminDigitalManager {
	
	

public byte[] getImageAdminDigitalManager(long  id) throws Exception;
public void updateImageAdminDigitalManager(long id , MultipartFile file) throws IOException;
}