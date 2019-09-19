package com.monetique.dto;


import java.io.Serializable;
import java.util.Date;


public class Jour implements Serializable{

	private static final long serialVersionUID = 1L;

	int jour;
	int mois;
	int annee;
	
	
	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public Jour(int jour, int mois, int annee) {
		super();
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	public Jour() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
