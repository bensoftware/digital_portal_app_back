package com.monetique.dto;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


public class Carte {
	private Long id;
	private String nomTitulaire;
	private String pan;
	private String soldeComptable;
	private String soldeDisponible;
	private String soldeBloque;
	private String dateReleve;
	private String du;
	private String au;
	private String agence;
	private List<TransactionMc> comptabiliser;
	private List<TransactionMc> bloquer;
	private HttpServletResponse response;
	
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomTitulaire() {
		return nomTitulaire;
	}
	public void setNomTitulaire(String nomTitulaire) {
		this.nomTitulaire = nomTitulaire;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getSoldeComptable() {
		return soldeComptable;
	}
	public void setSoldeComptable(String soldeComptable) {
		this.soldeComptable = soldeComptable;
	}
	public String getSoldeDisponible() {
		return soldeDisponible;
	}
	public void setSoldeDisponible(String soldeDisponible) {
		this.soldeDisponible = soldeDisponible;
	}
	public String getSoldeBloque() {
		return soldeBloque;
	}
	public void setSoldeBloque(String soldeBloque) {
		this.soldeBloque = soldeBloque;
	}
	public String getDateReleve() {
		return dateReleve;
	}
	public void setDateReleve(String dateReleve) {
		this.dateReleve = dateReleve;
	}
	public String getDu() {
		return du;
	}
	public void setDu(String du) {
		this.du = du;
	}
	public String getAu() {
		return au;
	}
	public void setAu(String au) {
		this.au = au;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public List<TransactionMc> getComptabiliser() {
		return comptabiliser;
	}
	public void setComptabiliser(List<TransactionMc> comptabiliser) {
		this.comptabiliser = comptabiliser;
	}
	public List<TransactionMc> getBloquer() {
		return bloquer;
	}
	public void setBloquer(List<TransactionMc> bloquer) {
		this.bloquer = bloquer;
	}
	public Carte() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
