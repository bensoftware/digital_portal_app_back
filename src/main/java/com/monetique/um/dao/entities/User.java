package com.monetique.um.dao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class User implements Serializable{

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 4740612708609554436L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String userName;
	
	@Column(length = 100, nullable = true)
	private String password;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	

	@Column(name = "date_creation")
	private Date dateCreation;

	@Column
	private boolean actif;
	
	@Column(name = "mdpamodifier")
	private boolean					mdpamodifier;
	
	@Column(name = "motdepasseprecedent", length = 100)
	private String				motdepasseprecedent;
	
	@Column(name = "datemodpass")
	private Date				dateModPass;
	
	@ManyToMany(fetch=FetchType.EAGER)
	Set<Rule> rules = new HashSet<>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	Set<Groupe> groupes = new HashSet<>();


	public User(Long id, String userName, String password, String nom, String prenom, String email, Date dateCreation,
			boolean actif) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateCreation = dateCreation;
		this.actif = actif;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password, String nom, String prenom, String email, Date dateCreation,
			boolean actif) {
		super();
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateCreation = dateCreation;
		this.actif = actif;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	

	public boolean isMdpamodifier() {
		return mdpamodifier;
	}

	public void setMdpamodifier(boolean mdpamodifier) {
		this.mdpamodifier = mdpamodifier;
	}

	public String getMotdepasseprecedent() {
		return motdepasseprecedent;
	}

	public void setMotdepasseprecedent(String motdepasseprecedent) {
		this.motdepasseprecedent = motdepasseprecedent;
	}

	public Date getDateModPass() {
		return dateModPass;
	}

	public void setDateModPass(Date dateModPass) {
		this.dateModPass = dateModPass;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}


	public Set<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

	
	
}
