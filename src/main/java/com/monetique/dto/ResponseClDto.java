package com.monetique.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ResponseClDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<ItemInfo> informationDebit;
	List<ItemInfo> informationCredit;

	double balance;

	boolean succes;
	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<ItemInfo> getInformationDebit() {
		return informationDebit;
	}

	public void setInformationDebit(List<ItemInfo> informationDebit) {
		this.informationDebit = informationDebit;
	}

	public List<ItemInfo> getInformationCredit() {
		return informationCredit;
	}

	public void setInformationCredit(List<ItemInfo> informationCredit) {
		this.informationCredit = informationCredit;
	}

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ResponseClDto(List<ItemInfo> informationDebit, List<ItemInfo> informationCredit,double balance, boolean succes) {
		super();
		this.balance=balance;
		this.informationDebit = informationDebit;
		this.informationCredit = informationCredit;
		this.succes = succes;
	}
	
	public ResponseClDto(List<ItemInfo> informationDebit,double balance, boolean succes) {
		super();
		this.balance=balance;
		this.informationDebit = informationDebit;
		this.succes = succes;
	}

}
