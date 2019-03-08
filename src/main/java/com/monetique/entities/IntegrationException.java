package com.monetique.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class IntegrationException implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;
	

	int code;
	String description;
	String codeSerie;
	Date dateIntegration;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCodeSerie() {
		return codeSerie;
	}
	public void setCodeSerie(String codeSerie) {
		this.codeSerie = codeSerie;
	}
	public Date getDateIntegration() {
		return dateIntegration;
	}
	public void setDateIntegration(Date dateIntegration) {
		this.dateIntegration = dateIntegration;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public IntegrationException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IntegrationException(int code, String description, String codeSerie, Date dateIntegration) {
		super();
		this.code = code;
		this.description = description;
		this.codeSerie = codeSerie;
		this.dateIntegration = dateIntegration;
	}
	
	
	
	
}
