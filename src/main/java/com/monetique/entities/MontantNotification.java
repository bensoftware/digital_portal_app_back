package com.monetique.entities;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class MontantNotification implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Long id;
	
	int nombre;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_montant")
	TypeMontant typeMontant;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_notification")
	Notification notification;

	
	
	
	public MontantNotification(int nombre, TypeMontant typeMontant, Notification notification) {
		super();
		this.nombre = nombre;
		this.typeMontant = typeMontant;
		this.notification = notification;
	}

	public MontantNotification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public TypeMontant getTypeMontant() {
		return typeMontant;
	}

	public void setTypeMontant(TypeMontant typeMontant) {
		this.typeMontant = typeMontant;
	}

	@JsonIgnore
	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	
	
}
