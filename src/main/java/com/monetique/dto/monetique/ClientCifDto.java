package com.monetique.dto.monetique;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ClientCifDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String apikey;
	private String nom;
	private String prenom;
	private String prenomPere;
	private String email;
	private String telephone;
	private String sexe;
	private String adresse;
	private String nomCarte;
	private String image;
	private String cif;
	private String nni;  
	private String dateNaissance;
	private String lieuNaissance;
	private String pUser;
	private TypeCardDto typeCardDto; // choix de la carte
	private AccountDtdo accountDtdo; // choix du compte 
    //Owned by the client
	private List<CardDto> listcardDtos;
	//Owned by the client
	private List<CardDto> listcardWaitingDtos;
	private List<AccountDtdo> listAccountDtdos; 
	//For displaying purpose in the front-end(list of TypeCard comming from select system database)
	private List<TypeCardDto> listTypeCardDtos;
	private String filename;


}
