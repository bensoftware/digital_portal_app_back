package com.monetique.dto;


import java.io.Serializable;
import java.util.Date;


public class ConsultationRecharge implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String operateur;
	double montant;
	double total;
	String origine;
	Date date;
	boolean expiration,epuisement;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public ConsultationRecharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public boolean isExpiration() {
		return expiration;
	}
	public void setExpiration(boolean expiration) {
		this.expiration = expiration;
	}
	public boolean isEpuisement() {
		return epuisement;
	}
	public void setEpuisement(boolean epuisement) {
		this.epuisement = epuisement;
	}
	public ConsultationRecharge(String operateur, double montant, double total, String origine) {
		super();
		this.operateur = operateur;
		this.montant = montant;
		this.total = total;
		this.origine = origine;
	}
	public ConsultationRecharge(String operateur, double montant, double total, Date date,boolean epuisement,boolean expiration) {
		super();
		this.operateur = operateur;
		this.montant = montant;
		this.total = total;
		this.date = date;
		this.epuisement=epuisement;
		this.expiration=expiration;

	}
	public ConsultationRecharge(String operateur, double montant, double total, String origine, Date date) {
		super();
		this.operateur = operateur;
		this.montant = montant;
		this.total = total;
		this.origine = origine;
		this.date = date;
	}
	
	
	
	
	
}
