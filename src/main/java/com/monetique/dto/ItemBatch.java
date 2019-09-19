package com.monetique.dto;


import java.io.Serializable;


public class ItemBatch implements Serializable{

	private static final long serialVersionUID = 1L;

	String nom;
	String status;
	boolean integrer;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isIntegrer() {
		return integrer;
	}
	public void setIntegrer(boolean integrer) {
		this.integrer = integrer;
	}
	public ItemBatch(String nom, String status, boolean integrer) {
		super();
		this.nom = nom;
		this.status = status;
		this.integrer = integrer;
	}
	
	
	
	
}
