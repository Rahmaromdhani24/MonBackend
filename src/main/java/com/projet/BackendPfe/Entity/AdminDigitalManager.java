package com.projet.BackendPfe.Entity;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value="AdminDigitalManager")
public class AdminDigitalManager extends User {
	
	@OneToMany(mappedBy = "adminDigitalManager", cascade = CascadeType.ALL)
	@JsonProperty(access =Access.WRITE_ONLY)
	private List<AdminMedicalManager> admins;
	
	
	public AdminDigitalManager(int cin ,String nom , String prenom ,String gender , String username, String email, String password  ,
			String reservePassword , String image  , Date date_inscription , String role) {
		super(cin , nom,prenom, gender ,username,  email,  password ,reservePassword,  image , date_inscription , role);
	}
	public AdminDigitalManager(String image) {
		this.image = image ;
	}
	public AdminDigitalManager() {
		super();
	}
	public Date getDate_inscription() {
		return date_inscription;
	}
	public List<AdminMedicalManager> getAdmins() {
		return admins;
	}
	public void setAdmins(List<AdminMedicalManager> admins) {
		this.admins = admins;
	}

	

}