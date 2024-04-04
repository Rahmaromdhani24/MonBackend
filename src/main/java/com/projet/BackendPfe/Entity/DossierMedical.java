package com.projet.BackendPfe.Entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DossierMedical")
public class DossierMedical {
	/** Associations de Classe Association **/
	   @ManyToOne
	    @JoinColumn(name = "adminMedical_id")
	    private AdminMedicalManager adminMedicalManagerDossier;

	    @ManyToOne
	    @JoinColumn(name = "medecin_id")
	    private Medecin medecinDossier;
	    
   /**  Association entre dossier medical et patient **/
	@OneToOne(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
	  private Patient patient;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long  idDossierMedical ;
    @Column(name="Diagnostic")
	private String diagnostic;
    @Column(name="Date_creation_dossier")
    private Date date_creation_dossier;
    
	public DossierMedical(long idDossierMedical, String diagnostic,
			Date date_creation_dossier ,AdminMedicalManager adminMedical,Patient patient , Medecin medecinDossier) {
		super();
		this.adminMedicalManagerDossier = adminMedical;
		this.idDossierMedical = idDossierMedical;
		this.diagnostic = diagnostic;
		this.date_creation_dossier = date_creation_dossier;
		this.patient=patient ; 
		this.medecinDossier=medecinDossier;
		
	}

	

	public AdminMedicalManager getAdminMedicalManagerDossier() {
		return adminMedicalManagerDossier;
	}



	public void setAdminMedicalManagerDossier(AdminMedicalManager adminMedicalManagerDossier) {
		this.adminMedicalManagerDossier = adminMedicalManagerDossier;
	}



	public Medecin getMedecinDossier() {
		return medecinDossier;
	}



	public void setMedecinDossier(Medecin medecinDossier) {
		this.medecinDossier = medecinDossier;
	}



	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public long getIdDossierMedical() {
		return idDossierMedical;
	}

	public void setIdDossierMedical(long idDossierMedical) {
		this.idDossierMedical = idDossierMedical;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public Date getDate_creation_dossier() {
		return date_creation_dossier;
	}

	public void setDate_creation_dossier(Date date_creation_dossier) {
		this.date_creation_dossier = date_creation_dossier;
	}	
	public DossierMedical() {
		super();
	}
}
