package com.monetique.dto;

import java.io.Serializable;
import java.util.List;

public class ImalResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	boolean cloture;
	String glCodeCloture;
	String nom;
	String status;
	List<Compte> comptes;
	
	
	
	public String getGlCodeCloture() {
		return glCodeCloture;
	}
	public void setGlCodeCloture(String glCodeCloture) {
		this.glCodeCloture = glCodeCloture;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isCloture() {
		return cloture;
	}
	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	public ImalResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ImalResponse(boolean cloture, String nom, List<Compte> comptes) {
		super();
		this.cloture = cloture;
		this.nom = nom;
		this.comptes = comptes;
	}
	
	
	
}
