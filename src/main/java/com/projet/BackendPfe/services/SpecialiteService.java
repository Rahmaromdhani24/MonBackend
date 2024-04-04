package com.projet.BackendPfe.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.projet.BackendPfe.Entity.Specialite;
import com.projet.BackendPfe.repository.SpecialiteRepository;

@Service
public class SpecialiteService  implements ISpecialite {


	@Autowired  SpecialiteRepository   repository ;
	@Override
	public byte[] getImageSpecialite(int id) throws Exception {
		
		String image =""; 
		Path p =Paths.get(System.getProperty("user.home")+"/Desktop/images projet web/",image);
		return Files.readAllBytes(p);
	}

	@Override
	public void updateImageSpecialite(int id, MultipartFile file) throws IOException {
		 Specialite  image =repository.findById(id).get(); 
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(System.getProperty("user.home")+"/Desktop/images projet web/",file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		// image.setImage(file.getOriginalFilename());
		 repository.save(image);

}

}