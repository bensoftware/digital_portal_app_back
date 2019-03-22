package com.monetique.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class CarteItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	String id;

    
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_expiration")
	Expiration expiration;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Expiration getExpiration() {
		return expiration;
	}


	public void setExpiration(Expiration expiration) {
		this.expiration = expiration;
	}


	public CarteItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CarteItem(String id, Expiration expiration) {
		super();
		this.id = id;
		this.expiration = expiration;
	}
	
	
	
	
	
	
	
	
	
	
}
