package com.monetique.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Notification implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;
	
	int typeNotification;
	int status;//0 : à l’initiation, 1: à visualisation du message par l’Admin, 2: à la clôture
	Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTypeNotification() {
		return typeNotification;
	}

	public void setTypeNotification(int typeNotification) {
		this.typeNotification = typeNotification;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	
	
	
	
}
