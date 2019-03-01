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
public class Operateur implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	int code;
	String libelle;
	
	@OneToMany(mappedBy="operateur",cascade=CascadeType.ALL)
	List<TypeMontant> typeMontants=new ArrayList<>();
	
	
	
	@JsonIgnore
	public List<TypeMontant> getTypeMontants() {
		return typeMontants;
	}

	public void setTypeMontants(List<TypeMontant> typeMontants) {
		this.typeMontants = typeMontants;
	}

	public int getCode() {
		return code;
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
