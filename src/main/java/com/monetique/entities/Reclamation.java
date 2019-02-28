package com.monetique.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Reclamation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;
	
	int etatActuel;
	int status;
	
	String libelle;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_etat_reclamation")
	EtatReclamation tache;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_carte_utilise")
	CarteUtilise carteUtilise;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEtatActuel() {
		return etatActuel;
	}

	public void setEtatActuel(int etatActuel) {
		this.etatActuel = etatActuel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public EtatReclamation getTache() {
		return tache;
	}

	public void setTache(EtatReclamation tache) {
		this.tache = tache;
	}

	public CarteUtilise getCarteUtilise() {
		return carteUtilise;
	}

	public void setCarteUtilise(CarteUtilise carteUtilise) {
		this.carteUtilise = carteUtilise;
	}
	
 
	
	
	
	
}
