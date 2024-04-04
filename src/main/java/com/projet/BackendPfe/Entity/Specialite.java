package com.projet.BackendPfe.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Specialite {
	 /**  Association entre medecin et Specialite **/
	 @OneToMany(mappedBy = "specialite", cascade = CascadeType.ALL)
	 @JsonProperty(access =Access.WRITE_ONLY)
	    private List<Medecin> medecins;

	 @Id  @GeneratedValue( strategy=GenerationType.IDENTITY )
	 private int id ; 
	 private String nom  ;
	 private Date date_creation ; 
	 
	 public Specialite( String nom ,Date date_creation ) {
			super();
			this.nom = nom;
			this.date_creation=date_creation;
		}
	 public Specialite(int id, String nom,Date date_creation ) {
			super();
			this.id = id;
			this.nom = nom;
			this.date_creation=date_creation;
		}

	public void setMedecins(List<Medecin> medecins) {
		this.medecins = medecins;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Specialite() {
		super();
	}
	
	public Specialite(String nom) {
		super();
		this.nom = nom;
	}
	public Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	@Override
	public String toString() {
		return "Specialite [medecins=" + medecins + ", id=" + id + ", nom=" + nom + ", image=" +  ", date_creation=" + date_creation + "]";
	}
	public List<Medecin> getMedecins() {
		return medecins;
	}
	
	
}
