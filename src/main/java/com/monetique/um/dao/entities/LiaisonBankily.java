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
	private boolean isRejeted;
	private String motifRejet;
	private String nomClient;
	private String serviceRequestId;
	
}
