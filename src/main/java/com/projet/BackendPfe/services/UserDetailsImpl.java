package com.projet.BackendPfe.services;

import java.util.Collection;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.BackendPfe.Entity.User;
public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private int cin;
	private String username;
	private String nom, prenom,gender;
	private String email;
	private String image ;
	private String role;
	@JsonIgnore
	private String password;
	
	  private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, int cin , String nom , String prenom ,String gender , String username, String email, String password,String role) {
		this.id = id;
		this.cin = cin;
		this.username = username;
		this.nom = nom;
		this.prenom=prenom;
		this.email = email;
		this.gender=gender;
		this.password = password;
		this.role     = role;
	}

	public static UserDetailsImpl build(User user) {
		
		return new UserDetailsImpl(
				user.getId(),
				user.getCin(),
				user.getNom(),
				user.getPrenom(),
				user.getGender(),
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
				user.getRole()
				);
	}

	

	public Long getId() {
		return id;
	}

	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	public String getImage() {
		return image;
	}

	public String getEmail() {
		return email;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
