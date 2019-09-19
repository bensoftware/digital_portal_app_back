package com.monetique.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Parametrage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;

	
	
	String urlInGimtel;
	String urlOutGimtel;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUrlInGimtel() {
		return urlInGimtel;
	}
	public void setUrlInGimtel(String urlInGimtel) {
		this.urlInGimtel = urlInGimtel;
	}
	public String getUrlOutGimtel() {
		return urlOutGimtel;
	}
	public void setUrlOutGimtel(String urlOutGimtel) {
		this.urlOutGimtel = urlOutGimtel;
	}
	
	
	
}
