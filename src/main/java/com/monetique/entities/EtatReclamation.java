package com.monetique.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class EtatReclamation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;
	String montif;
	Date date;
	int etat;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_liee")
	EtatReclamation liee;
	
	
	@OneToOne(mappedBy="liee",cascade=CascadeType.ALL)
	EtatReclamation precedent;
	
	@JsonIgnore
	public EtatReclamation getPrecedent() {
		return precedent;
	}

	public void setPrecedent(EtatReclamation precedent) {
		this.precedent = precedent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMontif() {
		return montif;
	}

	public void setMontif(String montif) {
		this.montif = montif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public EtatReclamation getLiee() {
		return liee;
	}

	public void setLiee(EtatReclamation liee) {
		this.liee = liee;
	}
	
	
	
}
