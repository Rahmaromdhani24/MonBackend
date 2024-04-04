package com.projet.BackendPfe.Entity;


import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Utilisateur",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"
				+ ""),
		@UniqueConstraint(columnNames = "email") 
	})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="roles")
public abstract class User {
	

	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	protected int cin;
		@Size(max = 100)
	  protected String username;
	   @Size(max = 100)
		  protected String nom;
	   @Size(max = 100)
		 protected String prenom;
	  @Size(max = 100)
	  @Email
	  protected String email;
	  protected String password;
	  protected String reservePassword;
	  @Column(name = "image", length = 1000000)
	  protected String image;
	  protected  Date  date_inscription  ; 
	  protected String role , gender ; 

	   public User(int cin , String nom , String prenom ,String gender , String username, String email, String password  , 
				String reservePassword , String image  , Date date_inscription  , String role) {
		    this.cin = cin;
		    this.nom = nom;
			this.prenom = prenom;
			this.gender=gender ;
			this.username = username;
			this.email = email;
			this.password = password;
			this.reservePassword = reservePassword;
			this.image = image;
			this.date_inscription=date_inscription ; 
			this.role = role  ; 
		
		}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getReservePassword() {
		return reservePassword;
	}

	public void setReservePassword(String reservePassword) {
		this.reservePassword = reservePassword;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate_inscription() {
		return date_inscription;
	}

	public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User() {
		super();
	}
	 
}