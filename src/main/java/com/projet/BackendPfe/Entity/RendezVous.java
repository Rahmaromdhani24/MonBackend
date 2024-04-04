package com.projet.BackendPfe.Entity;

import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Entity
public class RendezVous {
	  
    /*** Association entre Plage horaire et Rendezvous 
    @OneToOne(mappedBy = "rendezVous")
    private RendezVous rendezVous;
    ***/
	 @OneToOne(mappedBy = "rendezVous")
	 private PlageHorraire plageHorraire;
    /** classe Association entre patient et medecin **/
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecinRV;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientRV;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRdv;
    
    @Column(name ="dateRdv")
    private Date dateRdv;
    
    @Column(name ="datePrendreRdv")
    private Date datePrendreRdv;
    
    @Column(name ="description")
    private String description;


	public int getIdRdv() {
		return idRdv;
	}

	public void setIdRdv(int idRdv) {
		this.idRdv = idRdv;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	public Date getDatePrendreRdv() {
		return datePrendreRdv;
	}

	public void setDatePrendreRdv(Date datePrendreRdv) {
		this.datePrendreRdv = datePrendreRdv;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Medecin getMedecinRV() {
		return medecinRV;
	}

	public void setMedecinRV(Medecin medecinRV) {
		this.medecinRV = medecinRV;
	}

	public Patient getPatientRV() {
		return patientRV;
	}

	public void setPatientRV(Patient patientRV) {
		this.patientRV = patientRV;
	}

	public PlageHorraire getPlageHorraire() {
		return plageHorraire;
	}

	public void setPlageHorraire(PlageHorraire plageHorraire) {
		this.plageHorraire = plageHorraire;
	}
    
    
}
