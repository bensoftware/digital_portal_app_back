package com.monetique.dto;


import java.io.Serializable;


public class ItemTypeRecharge implements Serializable{

	private static final long serialVersionUID = 1L;
	
	long id;
	String operateur;
	double montant;
	boolean stockPerso;
	boolean active;
	int seuil;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public boolean isStockPerso() {
		return stockPerso;
	}
	public void setStockPerso(boolean stockPerso) {
		this.stockPerso = stockPerso;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getSeuil() {
		return seuil;
	}
	
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}
	
	public ItemTypeRecharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ItemTypeRecharge(long id,String operateur, double montant, boolean stockPerso, boolean active, int seuil) {
		super();
		this.id=id;
		this.operateur = operateur;
		this.montant = montant;
		this.stockPerso = stockPerso;
		this.active = active;
		this.seuil = seuil;
	}


	
	
	
}
