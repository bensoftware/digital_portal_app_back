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
	
	int seuilStock;
	int seuilExpiration;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSeuilStock() {
		return seuilStock;
	}
	public void setSeuilStock(int seuilStock) {
		this.seuilStock = seuilStock;
	}
	public int getSeuilExpiration() {
		return seuilExpiration;
	}
	public void setSeuilExpiration(int seuilExpiration) {
		this.seuilExpiration = seuilExpiration;
	}
	
   
	
	
}
