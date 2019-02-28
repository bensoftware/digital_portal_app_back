package com.monetique.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class CarteUtilise implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	String id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_montant")
	TypeMontant typeMontant;
	
	String cleNumeroSerie;
	String numeroCarte;
	String codeSecret;
	Date dateExpiration;
	String telephonePorteur;
	String telephoneBeneficiaire;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_expediteurt")
	Expediteur expediteur;
	
	
	
	public Expediteur getExpediteur() {
		return expediteur;
	}
	public void setExpediteur(Expediteur expediteur) {
		this.expediteur = expediteur;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TypeMontant getTypeMontant() {
		return typeMontant;
	}
	public void setTypeMontant(TypeMontant typeMontant) {
		this.typeMontant = typeMontant;
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
	public String getCodeSecret() {
		return codeSecret;
	}
	public void setCodeSecret(String codeSecret) {
		this.codeSecret = codeSecret;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	
	
	
	
	
}
