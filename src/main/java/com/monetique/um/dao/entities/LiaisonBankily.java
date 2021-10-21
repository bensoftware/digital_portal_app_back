package com.monetique.um.dao.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiaisonBankily {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean isApproved;
	private long idGroupe;
	private String idUserLiaison;
	private Date dateLiaison;
	private String nni;
	private String telephone;
	private String cif;
	private String compte;
	private String idUserApprobation;
	private String idUserRejet;
	private boolean isRejeted;
	private String motifRejet;
	private String nomClient;
	private String serviceRequestId;
	private String prenomClient;
	private String nomFamille;
	private String prenomPere;
	private Date dateApprobation;
	private Date dateRejet;
	private String imageUrl;
	private String document;
	public LiaisonBankily(long idGroupe, String idUserLiaison, Date dateLiaison, String nni, String telephone,
			String cif, String compte, String idUserApprobation, String nomClient, String prenomClient,
			String nomFamille, String prenomPere, Date dateApprobation, String imageUrl, String document) {
		super();
		this.idGroupe = idGroupe;
		this.idUserLiaison = idUserLiaison;
		this.dateLiaison = dateLiaison;
		this.nni = nni;
		this.telephone = telephone;
		this.cif = cif;
		this.compte = compte;
		this.idUserApprobation = idUserApprobation;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.nomFamille = nomFamille;
		this.prenomPere = prenomPere;
		this.dateApprobation = dateApprobation;
		this.imageUrl = imageUrl;
		this.document = document;
	}
	
	
	
	
}
