package com.projet.BackendPfe.request;


import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.*;
public class RegisterRequest {
	private String nom , prenom , reservePassword; 
	private String gender ;
	private String image ; 
	private int cin ;
	@NotBlank
    private String username;
    @NotBlank
   // @Size(min=0 , max = 100)
    @Email
    private String email;
    private Date date_inscription ; 
    // @NotBlank
    // private String role;
     @NotBlank
    // @Size( max = 10)
     private String password;
     private String role ; 
    public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	} 
	public String getImage() {
		return image;
	} 
 
	public void setGender(String gender) {
		this.gender = gender;
	}public void setImage(String image) {
		this.image = image;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getReservePassword() {
		return reservePassword;
	}

	public void setReservePassword(String reservePassword) {
		this.reservePassword = reservePassword;
	}

	public String getUsername() {
		return username;
	}

	public Date getDate_inscription() {
		return date_inscription;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}
	
}
