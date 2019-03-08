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
	String urlInMauritel;
	String urlOutMauritel;
	String urlInChinguitel;
	String urlOutChinguitel;
	String urlClePublique;
	
	
	
	public String getUrlClePublique() {
		return urlClePublique;
	}
	public void setUrlClePublique(String urlClePublique) {
		this.urlClePublique = urlClePublique;
	}
	public String getUrlInMauritel() {
		return urlInMauritel;
	}
	public void setUrlInMauritel(String urlInMauritel) {
		this.urlInMauritel = urlInMauritel;
	}
	public String getUrlOutMauritel() {
		return urlOutMauritel;
	}
	public void setUrlOutMauritel(String urlOutMauritel) {
		this.urlOutMauritel = urlOutMauritel;
	}
	public String getUrlInChinguitel() {
		return urlInChinguitel;
	}
	public void setUrlInChinguitel(String urlInChinguitel) {
		this.urlInChinguitel = urlInChinguitel;
	}
	public String getUrlOutChinguitel() {
		return urlOutChinguitel;
	}
	public void setUrlOutChinguitel(String urlOutChinguitel) {
		this.urlOutChinguitel = urlOutChinguitel;
	}
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
