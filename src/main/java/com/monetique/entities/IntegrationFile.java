package com.monetique.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class IntegrationFile implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;
	
	String filename;
	Date date;
	int carteNonIntegrer;
	int totalCarte;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_operateur")
	Operateur operateur;
	
	
	

	public IntegrationFile(String filename, Date date, int carteNonIntegrer, int totalCarte, Operateur operateur) {
		super();
		this.filename = filename;
		this.date = date;
		this.carteNonIntegrer = carteNonIntegrer;
		this.totalCarte = totalCarte;
		this.operateur = operateur;
	}

	public IntegrationFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonIgnore
	public Operateur getOperateur() {
		return operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCarteNonIntegrer() {
		return carteNonIntegrer;
	}

	public void setCarteNonIntegrer(int carteNonIntegrer) {
		this.carteNonIntegrer = carteNonIntegrer;
	}

	public int getTotalCarte() {
		return totalCarte;
	}

	public void setTotalCarte(int totalCarte) {
		this.totalCarte = totalCarte;
	}
	
	
	
	


	
	
	
	
}
