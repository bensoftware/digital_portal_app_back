package com.monetique.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<ItemInfo> infrmationClearing;


	String nom;
	String cif;
	String statusI;
	String statusB;
	String glCodeCloture;
	String nomBankily;
	String nomImalCible;
	String addRefLiaison;
	List<Compte> comptes;
	List<Historique> historiques;
	
	String loginId;
	String otp;
	int statusUser;
	String filename;
	boolean historique;
	boolean succes;
	
	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getStatusI() {
		return statusI;
	}

	public void setStatusI(String statusI) {
		this.statusI = statusI;
	}

	public String getStatusB() {
		return statusB;
	}

	public void setStatusB(String statusB) {
		this.statusB = statusB;
	}

	public String getGlCodeCloture() {
		return glCodeCloture;
	}

	public void setGlCodeCloture(String glCodeCloture) {
		this.glCodeCloture = glCodeCloture;
	}

	public String getNomBankily() {
		return nomBankily;
	}

	public void setNomBankily(String nomBankily) {
		this.nomBankily = nomBankily;
	}

	public String getNomImalCible() {
		return nomImalCible;
	}

	public void setNomImalCible(String nomImalCible) {
		this.nomImalCible = nomImalCible;
	}

	public String getAddRefLiaison() {
		return addRefLiaison;
	}

	public void setAddRefLiaison(String addRefLiaison) {
		this.addRefLiaison = addRefLiaison;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public List<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(List<Historique> historiques) {
		this.historiques = historiques;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public int getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(int statusUser) {
		this.statusUser = statusUser;
	}

	public boolean isHistorique() {
		return historique;
	}

	public void setHistorique(boolean historique) {
		this.historique = historique;
	}

	public List<ItemInfo> getInfrmationClearing() {
		return infrmationClearing;
	}

	public void setInfrmationClearing(List<ItemInfo> infrmationClearing) {
		this.infrmationClearing = infrmationClearing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	

}
