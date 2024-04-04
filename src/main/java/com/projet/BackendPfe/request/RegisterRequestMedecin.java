package com.projet.BackendPfe.request;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.projet.BackendPfe.Entity.AdminMedicalManager;

public class RegisterRequestMedecin {

	private int cin ; 
	@NotBlank
	private String nom , prenom , reservePassword ; 
	private String gender ;
	private byte[] image ; 
	private long telephone ; 
	@NotBlank
    private String username;
    @NotBlank
   // @Size(min=0 , max = 100)
    @Email
    private String email;
  //  @NotBlank
   // private String role;
    @NotBlank
    //@Size( max = 10)
    private String password;
    private Date date_inscription ; 
    private String role ; 
  	public Date getDate_inscription() {
  		return date_inscription;
  	}
    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@ManyToOne
	private AdminMedicalManager admin;
	public AdminMedicalManager getAdmin() {
		return admin;
	}
	public void setAdmin(AdminMedicalManager admin) {
		this.admin = admin;
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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	} 
	public byte[] getImage() {
		return image;
	} 
	public long getTelephone() {
		return telephone;
	} 
	public void setGender(String gender) {
		this.gender = gender;
	}public void setImage(byte[] image) {
		this.image = image;
	}public void setTelephone(long telephone) {
		this.telephone = telephone;
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
}


