package com.monetique.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ClearingFile implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	String nom; // ref unique


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ClearingFile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ClearingFile(String nom) {
		super();
		this.nom = nom;
	}
	

	
	
	
}
