package com.monetique.dto;

import java.io.Serializable;

public class RequestNotification implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String message;
	String telephone;
	String email;
	String objet;
	
	
	
	public RequestNotification(String message, String telephone, String email, String objet) {
		super();
		this.message = message;
		this.telephone = telephone;
		this.email = email;
		this.objet = objet;
	}
	public RequestNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	

}
