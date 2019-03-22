package com.monetique.dto;


import java.io.Serializable;
import java.util.Date;


public class NotificationItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long id;
	int typeNotification;
	String titre;
	Date date;
	double montant;
	int designation;
	int status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getTypeNotification() {
		return typeNotification;
	}
	public void setTypeNotification(int typeNotification) {
		this.typeNotification = typeNotification;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public int getDesignation() {
		return designation;
	}
	public void setDesignation(int designation) {
		this.designation = designation;
	}
	public NotificationItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public NotificationItem(long id, int typeNotification, String titre, Date date, double montant, int designation) {
		super();
		this.id = id;
		this.typeNotification = typeNotification;
		this.titre = titre;
		this.date = date;
		this.montant = montant;
		this.designation = designation;
	}
	public NotificationItem(long id, int typeNotification, String titre, Date date, double montant, int designation,
			int status) {
		super();
		this.id = id;
		this.typeNotification = typeNotification;
		this.titre = titre;
		this.date = date;
		this.montant = montant;
		this.designation = designation;
		this.status = status;
	}
	
	
	
	
}
