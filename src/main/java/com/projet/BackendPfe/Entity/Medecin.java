package com.projet.BackendPfe.Entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value="Medecin")

public class Medecin extends User {
	protected long telephone ;
	
    /**  Association entre medecin et Admin Medical **/
	  @ManyToOne
	  private AdminMedicalManager admin;
    /**  Association entre medecin et Specialite **/
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "specialite_id")
	  private Specialite specialite;
	/*** Association entre medecin et leur plage horraire ***/  
	  @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
	  private List<PlageHorraire> plageHorraires = new ArrayList<PlageHorraire>();
	/**  Association entre Admin medical et Dossier Medical **/
	  @OneToMany(mappedBy = "medecinDossier")
	    private List<DossierMedical> liste_DossierMedical = new ArrayList<DossierMedical>();
    /**** classe Association Rendezvous entre medecin et patient ****/
	  @OneToMany(mappedBy = "medecinRV")
	    private List<RendezVous> liste_RendezVous= new ArrayList<RendezVous>();
	  /**** classe Association Operation entre medecin et patient ****/
	  @OneToMany(mappedBy = "medecin")
	  @JsonProperty(access =Access.WRITE_ONLY)
	  private List<Operation> liste_operations = new ArrayList<Operation>();

	

	  public Medecin() {
			super();
		}
	public Medecin(Specialite specialite,int cin , String nom , String prenom ,String username, String email, String password, 
			String reservePassword ,String gender, long telephone , String image, Date date_inscription , String role, AdminMedicalManager admin   ) {
		super(cin , nom , prenom ,gender,username,email,password,reservePassword , image , date_inscription , role );
		this.telephone=telephone;
		this.admin=admin ; 
	    this.specialite=specialite;
		
	}

	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}

	public AdminMedicalManager getAdmin() {
		return admin;
	}

	public void setAdmin(AdminMedicalManager admin) {
		this.admin = admin;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public List<PlageHorraire> getPlageHorraires() {
		return plageHorraires;
	}

	public void setPlageHorraires(List<PlageHorraire> plageHorraires) {
		this.plageHorraires = plageHorraires;
	}
	public List<DossierMedical> getListe_DossierMedical() {
		return liste_DossierMedical;
	}
	public void setListe_DossierMedical(List<DossierMedical> liste_DossierMedical) {
		this.liste_DossierMedical = liste_DossierMedical;
	}
	public List<RendezVous> getListe_RendezVous() {
		return liste_RendezVous;
	}
	public void setListe_RendezVous(List<RendezVous> liste_RendezVous) {
		this.liste_RendezVous = liste_RendezVous;
	}
	public List<Operation> getListe_operations() {
		return liste_operations;
	}
	public void setListe_operations(List<Operation> liste_operations) {
		this.liste_operations = liste_operations;
	}
	
	}
		 

