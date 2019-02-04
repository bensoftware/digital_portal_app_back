package com.monetique.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Clearing implements Serializable{

	private static final long serialVersionUID = 1L;

	 @EmbeddedId
	 Reference reference;
	
	String typeEnregistrement;
	String numeroDeSerie; 
	String codeOperation;
	Date dateDeProcessing;
	String numeroDeCommercant;
	String numeroDuTerminal;
	String compteCommercant;
	String nomCommercant;
	String locationCommercant;
	String territoire;
	String pan;
	String numeroComptePorteur;
	String dateDexpiration;
	String flagDopposition;
	int index;

	String referenceTransaction; // ref unique
	
	String numeroDeRemise;
	String dateRemise;
	String numeroTransaction;
	String dateDeTransaction;

	String numeroAutorisation;   // numeroAuth
	
	String montantDautorisation;
	String montantTransactionGross;
	String monnaie;
	String exposantMonnaie;
	String fraisInterchange;
	String signeFraisInterchange;
	String commissionFraisPorteur;
	String montantNetCreditCommercant;
	String commissionCommercantCredit;
	String codeBanqueEmettrice;
	String codeBanqueAcquereur;
	String fraisConversion;
	String codeCategirieCommercant;
	String codeSysteme;
	String fraisCentre;
	String statusTransaction;
	String notUsed;
	String referenceAutorisation; // pour auth 
	
	String montantSetllment ;
	String deviseSellment;
	String reste;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idCl")
	ClearingFile clearingFile;
	
	
	

	public String getMontantSetllment() {
		return montantSetllment;
	}
	public void setMontantSetllment(String montantSetllment) {
		this.montantSetllment = montantSetllment;
	}
	public String getDeviseSellment() {
		return deviseSellment;
	}
	public void setDeviseSellment(String deviseSellment) {
		this.deviseSellment = deviseSellment;
	}
	public String getReste() {
		return reste;
	}
	public void setReste(String reste) {
		this.reste = reste;
	}
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ClearingFile getClearingFile() {
		return clearingFile;
	}
	public void setClearingFile(ClearingFile clearingFile) {
		this.clearingFile = clearingFile;
	}
	public String getMontantNetCreditCommercant() {
		return montantNetCreditCommercant;
	}
	public void setMontantNetCreditCommercant(String montantNetCreditCommercant) {
		this.montantNetCreditCommercant = montantNetCreditCommercant;
	}
	public String getCommissionCommercantCredit() {
		return commissionCommercantCredit;
	}
	public void setCommissionCommercantCredit(String commissionCommercantCredit) {
		this.commissionCommercantCredit = commissionCommercantCredit;
	}
	public String getTypeEnregistrement() {
		return typeEnregistrement;
	}
	public void setTypeEnregistrement(String typeEnregistrement) {
		this.typeEnregistrement = typeEnregistrement;
	}
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	public String getCodeOperation() {
		return codeOperation;
	}
	public void setCodeOperation(String codeOperation) {
		this.codeOperation = codeOperation;
	}
	public Date getDateDeProcessing() {
		return dateDeProcessing;
	}
	public void setDateDeProcessing(Date dateDeProcessing) {
		this.dateDeProcessing = dateDeProcessing;
	}
	public String getNumeroDeCommercant() {
		return numeroDeCommercant;
	}
	public void setNumeroDeCommercant(String numeroDeCommercant) {
		this.numeroDeCommercant = numeroDeCommercant;
	}
	public String getNumeroDuTerminal() {
		return numeroDuTerminal;
	}
	public void setNumeroDuTerminal(String numeroDuTerminal) {
		this.numeroDuTerminal = numeroDuTerminal;
	}
	public String getCompteCommercant() {
		return compteCommercant;
	}
	public void setCompteCommercant(String compteCommercant) {
		this.compteCommercant = compteCommercant;
	}
	public String getNomCommercant() {
		return nomCommercant;
	}
	public void setNomCommercant(String nomCommercant) {
		this.nomCommercant = nomCommercant;
	}
	public String getLocationCommercant() {
		return locationCommercant;
	}
	public void setLocationCommercant(String locationCommercant) {
		this.locationCommercant = locationCommercant;
	}
	public String getTerritoire() {
		return territoire;
	}
	public void setTerritoire(String territoire) {
		this.territoire = territoire;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getNumeroComptePorteur() {
		return numeroComptePorteur;
	}
	public void setNumeroComptePorteur(String numeroComptePorteur) {
		this.numeroComptePorteur = numeroComptePorteur;
	}
	public String getDateDexpiration() {
		return dateDexpiration;
	}
	public void setDateDexpiration(String dateDexpiration) {
		this.dateDexpiration = dateDexpiration;
	}
	public String getFlagDopposition() {
		return flagDopposition;
	}
	public void setFlagDopposition(String flagDopposition) {
		this.flagDopposition = flagDopposition;
	}
	public String getReferenceTransaction() {
		return referenceTransaction;
	}
	public void setReferenceTransaction(String referenceTransaction) {
		this.referenceTransaction = referenceTransaction;
	}
	public String getNumeroDeRemise() {
		return numeroDeRemise;
	}
	public void setNumeroDeRemise(String numeroDeRemise) {
		this.numeroDeRemise = numeroDeRemise;
	}
	public String getDateRemise() {
		return dateRemise;
	}
	public void setDateRemise(String dateRemise) {
		this.dateRemise = dateRemise;
	}
	public String getNumeroTransaction() {
		return numeroTransaction;
	}
	public void setNumeroTransaction(String numeroTransaction) {
		this.numeroTransaction = numeroTransaction;
	}
	public String getDateDeTransaction() {
		return dateDeTransaction;
	}
	public void setDateDeTransaction(String dateDeTransaction) {
		this.dateDeTransaction = dateDeTransaction;
	}
	public String getNumeroAutorisation() {
		return numeroAutorisation;
	}
	public void setNumeroAutorisation(String numeroAutorisation) {
		this.numeroAutorisation = numeroAutorisation;
	}
	public String getMontantDautorisation() {
		return montantDautorisation;
	}
	public void setMontantDautorisation(String montantDautorisation) {
		this.montantDautorisation = montantDautorisation;
	}
	public String getMontantTransactionGross() {
		return montantTransactionGross;
	}
	public void setMontantTransactionGross(String montantTransactionGross) {
		this.montantTransactionGross = montantTransactionGross;
	}
	public String getMonnaie() {
		return monnaie;
	}
	public void setMonnaie(String monnaie) {
		this.monnaie = monnaie;
	}
	public String getExposantMonnaie() {
		return exposantMonnaie;
	}
	public void setExposantMonnaie(String exposantMonnaie) {
		this.exposantMonnaie = exposantMonnaie;
	}
	public String getFraisInterchange() {
		return fraisInterchange;
	}
	public void setFraisInterchange(String fraisInterchange) {
		this.fraisInterchange = fraisInterchange;
	}
	public String getSigneFraisInterchange() {
		return signeFraisInterchange;
	}
	public void setSigneFraisInterchange(String signeFraisInterchange) {
		this.signeFraisInterchange = signeFraisInterchange;
	}
	public String getCommissionFraisPorteur() {
		return commissionFraisPorteur;
	}
	public void setCommissionFraisPorteur(String commissionFraisPorteur) {
		this.commissionFraisPorteur = commissionFraisPorteur;
	}
	public String getCodeBanqueEmettrice() {
		return codeBanqueEmettrice;
	}
	public void setCodeBanqueEmettrice(String codeBanqueEmettrice) {
		this.codeBanqueEmettrice = codeBanqueEmettrice;
	}
	public String getCodeBanqueAcquereur() {
		return codeBanqueAcquereur;
	}
	public void setCodeBanqueAcquereur(String codeBanqueAcquereur) {
		this.codeBanqueAcquereur = codeBanqueAcquereur;
	}
	public String getFraisConversion() {
		return fraisConversion;
	}
	public void setFraisConversion(String fraisConversion) {
		this.fraisConversion = fraisConversion;
	}
	public String getCodeCategirieCommercant() {
		return codeCategirieCommercant;
	}
	public void setCodeCategirieCommercant(String codeCategirieCommercant) {
		this.codeCategirieCommercant = codeCategirieCommercant;
	}
	public String getCodeSysteme() {
		return codeSysteme;
	}
	public void setCodeSysteme(String codeSysteme) {
		this.codeSysteme = codeSysteme;
	}
	public String getFraisCentre() {
		return fraisCentre;
	}
	public void setFraisCentre(String fraisCentre) {
		this.fraisCentre = fraisCentre;
	}
	public String getStatusTransaction() {
		return statusTransaction;
	}
	public void setStatusTransaction(String statusTransaction) {
		this.statusTransaction = statusTransaction;
	}
	public String getNotUsed() {
		return notUsed;
	}
	public void setNotUsed(String notUsed) {
		this.notUsed = notUsed;
	}
	public String getReferenceAutorisation() {
		return referenceAutorisation;
	}
	public void setReferenceAutorisation(String referenceAutorisation) {
		this.referenceAutorisation = referenceAutorisation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
