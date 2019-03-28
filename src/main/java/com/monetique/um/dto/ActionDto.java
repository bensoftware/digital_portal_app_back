package com.monetique.um.dto;

import java.io.Serializable;

/**
 * Modèle Entité Action Dto.
 *
 * @author bpm digital
 *
 */
public class ActionDto implements Serializable{

	 private static final long serialVersionUID = 1L;

	 private Long id;
	 private String libelle;
	 
	 
	public ActionDto(Long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	 
	 
}
