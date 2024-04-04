package com.projet.BackendPfe.request;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequestAdmin {
	
	private int cin ;
	private byte[] image ; 
	private String gender;
	@NotBlank
    private String nom;	
	@NotBlank
    private String prenom;	
	@NotBlank
    private String username;
	@NotBlank
    private String reservePassword;
    @NotBlank
   // @Size(min=0 , max = 100)
    @Email
    private String email;
   // @NotBlank
   // private String role;
    @NotBlank
   // @Size( max = 10)
    private String password;
    private Date date_inscription ; 
	public Date getDate_inscription() {
		return date_inscription;
	}
	public String getUsername() {
		return username;
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
	/*public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}*/
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public byte[] getImage() {
		return image;
	} 
	
	public void setImage(byte[] image) {
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
	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}
	
}




