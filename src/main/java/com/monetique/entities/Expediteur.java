package com.monetique.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Expediteur implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	int code;
	String libelle;
	
	@OneToMany(mappedBy="expediteur",cascade=CascadeType.ALL)
	List<CarteUtilise> carteUtilises=new ArrayList<>();
	
	
	
	@JsonIgnore
	public int getCode() {
		return code;
	}
	
	public List<CarteUtilise> getCarteUtilises() {
		return carteUtilises;
	}

	public void setCarteUtilises(List<CarteUtilise> carteUtilises) {
		this.carteUtilises = carteUtilises;
	}

	public void setCode(int code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
	
	
	
}
