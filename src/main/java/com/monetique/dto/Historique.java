package com.monetique.dto;


import java.io.Serializable;
import java.util.Date;

public class Historique implements Serializable{

	private static final long serialVersionUID = 1L;


	 long id;
	
	 String telephone;
	 String cif;
	 String additionalReference;
	 Date dateCreation=new Date();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getAdditionalReference() {
		return additionalReference;
	}
	public void setAdditionalReference(String additionalReference) {
		this.additionalReference = additionalReference;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	 
	
}
