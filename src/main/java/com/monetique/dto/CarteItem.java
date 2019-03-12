package com.monetique.dto;


import java.io.Serializable;


public class CarteItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String identifiant;
	String cleNumeroSerie;
	String numeroCarte;
	String telephonePorteur;
	String telephoneBeneficiaire;
	double montant;
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getCleNumeroSerie() {
		return cleNumeroSerie;
	}
	public void setCleNumeroSerie(String cleNumeroSerie) {
		this.cleNumeroSerie = cleNumeroSerie;
	}
	public String getNumeroCarte() {
		return numeroCarte;
	}
	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}
	public String getTelephonePorteur() {
		return telephonePorteur;
	}
	public void setTelephonePorteur(String telephonePorteur) {
		this.telephonePorteur = telephonePorteur;
	}
	public String getTelephoneBeneficiaire() {
		return telephoneBeneficiaire;
	}
	public void setTelephoneBeneficiaire(String telephoneBeneficiaire) {
		this.telephoneBeneficiaire = telephoneBeneficiaire;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public CarteItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarteItem(String identifiant, String cleNumeroSerie, String numeroCarte, String telephonePorteur,
			String telephoneBeneficiaire, double montant) {
		super();
		this.identifiant = identifiant;
		this.cleNumeroSerie = cleNumeroSerie;
		this.numeroCarte = numeroCarte;
		this.telephonePorteur = telephonePorteur;
		this.telephoneBeneficiaire = telephoneBeneficiaire;
		this.montant = montant;
	}
	
	
	
	
}
