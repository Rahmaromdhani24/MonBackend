package com.projet.BackendPfe.Entity;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value="Operation")
public class Operation {
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "medecin_id")
	    private Medecin medecin;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "patient_id")
	    private Patient patient;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idOperation;
	    private String typeOperation;
	    private String cause ;
	    private String description_Operation;
	    private Date date_operation ; 
	    
		public Operation(Patient patient, Medecin medecin ,Date date_operation, String typeOperation, String description_Operation , String cause) {
			super();
			this.patient = patient;
			this.typeOperation = typeOperation;
			this.description_Operation = description_Operation;
			this.medecin = medecin ; 
			this.cause=cause ; 
			this.date_operation=date_operation;
		}
		
		public Operation() {
			super();
		}

		public Patient getPatient() {
			return patient;
		}
		public void setPatient(Patient patient) {
			this.patient = patient;
		}
		public int getIdOperation() {
			return idOperation;
		}
		public void setIdOperation(int idOperation) {
			this.idOperation = idOperation;
		}
		public String getTypeOperation() {
			return typeOperation;
		}
		public void setTypeOperation(String typeOperation) {
			this.typeOperation = typeOperation;
		}
		public String getDescription_Operation() {
			return description_Operation;
		}
		public void setDescription_Operation(String description_Operation) {
			this.description_Operation = description_Operation;
		}


		public Date getDate_operation() {
			return date_operation;
		}

		public void setDate_operation(Date date_operation) {
			this.date_operation = date_operation;
		}

		public Medecin getMedecin() {
			return medecin;
		}

		public void setMedecin(Medecin medecin) {
			this.medecin = medecin;
		}

		public String getCause() {
			return cause;
		}

		public void setCause(String cause) {
			this.cause = cause;
		}

	    
}
