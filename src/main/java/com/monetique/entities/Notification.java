package com.monetique.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Notification implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;
	
	int typeNotification;
	int status;
	Date date;
	
	@OneToMany(mappedBy="notification",cascade=CascadeType.ALL)
	List<MontantNotification> montants=new ArrayList<>();


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

	public List<MontantNotification> getMontants() {
		return montants;
	}

	public void setMontants(List<MontantNotification> montants) {
		this.montants = montants;
	}


	
	
	
	
}
