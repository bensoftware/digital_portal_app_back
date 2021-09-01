package com.monetique.dto;

public class TransactionMc{
	private String dateTransaction="";
	private String typeTransaction=""; // 1 (achat,retrait) ,2 recharge, 3 clearing 
	private String lieuTransaction="";
	private String dateComptabilisation="";
	private String devise="";
	private String montantTransaction="";
	private String commission="";
	private String montantLocale="";
	private String total="";
	public String getDateTransaction() {
		return dateTransaction;
	}
	public void setDateTransaction(String dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
	public String getTypeTransaction() {
		return typeTransaction;
	}
	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}
	public String getLieuTransaction() {
		return lieuTransaction;
	}
	public void setLieuTransaction(String lieuTransaction) {
		this.lieuTransaction = lieuTransaction;
	}
	public String getDateComptabilisation() {
		return dateComptabilisation;
	}
	public void setDateComptabilisation(String dateComptabilisation) {
		this.dateComptabilisation = dateComptabilisation;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}
	public String getMontantTransaction() {
		return montantTransaction;
	}
	public void setMontantTransaction(String montantTransaction) {
		this.montantTransaction = montantTransaction;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getMontantLocale() {
		return montantLocale;
	}
	public void setMontantLocale(String montantLocale) {
		this.montantLocale = montantLocale;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public TransactionMc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
