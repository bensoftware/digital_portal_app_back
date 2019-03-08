package com.monetique.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class TypeMontant implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;
	
	double montant;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_operateur")
	Operateur operateur;
	
	
	
	
	
	/*@OneToMany(mappedBy="typeMontant",cascade=CascadeType.ALL)
	List<CarteStock> carteStocks=new ArrayList<>();


	@OneToMany(mappedBy="typeMontant",cascade=CascadeType.ALL)
	List<CarteUtilise> carteUtilises=new ArrayList<>();

	@OneToMany(mappedBy="typeMontant",cascade=CascadeType.ALL)
	List<Notification> notifications=new ArrayList<>();
	*/
	
	
/*	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	@JsonIgnore
	public List<CarteStock> getCarteStocks() {
		return carteStocks;
	}

	public void setCarteStocks(List<CarteStock> carteStocks) {
		this.carteStocks = carteStocks;
	}
	
	@JsonIgnore
	public List<CarteUtilise> getCarteUtilises() {
		return carteUtilises;
	}

	public void setCarteUtilises(List<CarteUtilise> carteUtilises) {
		this.carteUtilises = carteUtilises;
	}*/
	
	public TypeMontant(double montant, Operateur operateur) {
		super();
		this.montant = montant;
		this.operateur = operateur;
	}

	public TypeMontant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operateur getOperateur() {
		return operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}

	



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
	
	
	
	
	
}
