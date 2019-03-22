package com.monetique.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Expiration {


	@Id
	@GeneratedValue
	long id;
	
	Date dateCreation;
	int total;
	Date dateMaj;
	boolean maj;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getDateMaj() {
		return dateMaj;
	}
	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}
	public boolean isMaj() {
		return maj;
	}
	public void setMaj(boolean maj) {
		this.maj = maj;
	}
	
	
	
}
