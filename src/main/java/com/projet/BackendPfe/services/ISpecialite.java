package com.projet.BackendPfe.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ISpecialite {
	
	 public byte[] getImageSpecialite(int id) throws Exception;
		
	 public void updateImageSpecialite(int id , MultipartFile file) throws IOException;
	 
	

}
