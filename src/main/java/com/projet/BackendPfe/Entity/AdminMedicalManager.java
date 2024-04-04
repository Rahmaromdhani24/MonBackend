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
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value="AdminMedicalManager")

public class AdminMedicalManager extends User {
	 /**  Association entre Admin medical et Admin Digital **/
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "adminDigital_id")
	  private AdminDigitalManager adminDigitalManager;
	  /**  Association entre Admin medical et medecins **/
	  @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	  @JsonProperty(access =Access.WRITE_ONLY)
	  private List<Medecin>liste_experts=new ArrayList<Medecin>();
	  /**  Association entre Admin medical et patient **/
	  @OneToMany(mappedBy = "adminMedical", cascade = CascadeType.ALL)
	  @JsonProperty(access =Access.WRITE_ONLY)
	  private List<Patient> patients=new ArrayList<Patient>();
	  /**  Association entre Admin medical et Dossier Medical **/
	  @OneToMany(mappedBy = "adminMedicalManagerDossier")
	    private List<DossierMedical> liste_DossierMedical = new ArrayList<DossierMedical>();

	public AdminMedicalManager() {
		super();
	}

	public AdminMedicalManager (int cin ,String nom , String prenom ,String gender,String username, String email, String password  ,String reservePassword , 
			String image  , Date date_inscription , String role ,AdminDigitalManager  adminDigitalManager) {
		super(cin,nom , prenom ,gender,username,email,password,reservePassword , image , date_inscription , role );
	
		this.adminDigitalManager=adminDigitalManager;}
		
		public AdminMedicalManager(String image) {
			this.image = image ;
		}


		public List<Medecin> getListe_experts() {
			return liste_experts;
		}

		public void setListe_experts(List<Medecin> liste_experts) {
			this.liste_experts = liste_experts;
		}

		public List<Patient> getPatients() {
			return patients;
		}

		public void setPatients(List<Patient> patients) {
			this.patients = patients;
		}

		public AdminDigitalManager getAdminDigitalManager() {
			return adminDigitalManager;
		}

		public void setAdminDigitalManager(AdminDigitalManager adminDigitalManager) {
			this.adminDigitalManager = adminDigitalManager;
		}


		public List<DossierMedical> getListe_DossierMedical() {
			return liste_DossierMedical;
		}

		public void setListe_DossierMedical(List<DossierMedical> liste_DossierMedical) {
			this.liste_DossierMedical = liste_DossierMedical;
		}
		
		
			
}
