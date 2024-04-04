package com.projet.BackendPfe.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value="Patient")
public class Patient extends User {

	protected String  antecedants;
	protected long telephone ;
	protected Date date_naissance ; 
	
	/**  Association entre Admin medical et patient **/
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "adminMedical_id")
	  private AdminMedicalManager adminMedical;
	  /**  Association entre dossier medical et patient **/
	  @OneToOne
	  @JoinColumn(name ="idDossier")
	  private DossierMedical dossierMedical;
	  /**** classe Association Rendezvous entre medecin et patient ****/
	  @OneToMany(mappedBy = "patientRV")
	  private List<RendezVous> liste_RendezVous= new ArrayList<RendezVous>();
	  /**** classe Association Operation entre medecin et patient ****/
	  @OneToMany(mappedBy = "patient")
	  @JsonProperty(access =Access.WRITE_ONLY)
	  private List<Operation> liste_operations = new ArrayList<Operation>();
	
	public Patient( int cin , String nom , String prenom ,String gender,String username, String email, String password,
			String reservePassword , long telephone ,String image, Date date_inscription ,
			String role, AdminMedicalManager adminMedical ,Date date_naissance ,String antecedants  ) {
		 
		super(cin , nom , prenom , gender,username,email,password,reservePassword , image , date_inscription , role );
	
		this.antecedants=antecedants;
		this.date_naissance=date_naissance;
		this.telephone=telephone;
		this.adminMedical=adminMedical ; 
	}

	public String getAntecedants() {
		return antecedants;
	}

	public void setAntecedants(String antecedants) {
		this.antecedants = antecedants;
	}

	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public AdminMedicalManager getAdminMedical() {
		return adminMedical;
	}

	public void setAdminMedical(AdminMedicalManager adminMedical) {
		this.adminMedical = adminMedical;
	}

	public DossierMedical getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(DossierMedical dossierMedical) {
		this.dossierMedical = dossierMedical;
	}

	public Patient() {
		super();
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