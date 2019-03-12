package com.monetique.dto;


import java.io.Serializable;


public class ConsultationRecharge implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String operateur;
	double montant;
	double total;
	String origine;
	
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
	public ConsultationRecharge(String operateur, double montant, double total, String origine) {
		super();
		this.operateur = operateur;
		this.montant = montant;
		this.total = total;
		this.origine = origine;
	}
	public ConsultationRecharge(String operateur, double montant, double total) {
		super();
		this.operateur = operateur;
		this.montant = montant;
		this.total = total;
	}
	
	
	
	
	
}
