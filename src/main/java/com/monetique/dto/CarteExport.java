package com.monetique.dto;

import java.io.Serializable;
import java.util.Date;

public class CarteExport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String numSerie;
	double montant;
	Date dateExpiration;
	
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public CarteExport(String numSerie, double montant, Date dateExpiration) {
		super();
		this.numSerie = numSerie;
		this.montant = montant;
		this.dateExpiration = dateExpiration;
	}
	public CarteExport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
