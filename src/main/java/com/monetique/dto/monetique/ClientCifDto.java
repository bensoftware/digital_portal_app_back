package com.monetique.dto.monetique;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ClientCifDto implements Serializable {

	public String nom;
	public String prenom;
	public String email;
	public String telephone;
	public String sexe;
	public String adresse;
	public String nomCarte;
    public String image;
    
    public List<CardDto> listcardDtos;
    
	public List<AccountDtdo> listAccountDtdos;
   	
	//For displaying purpose nin the front-end(list of TypeCard comming from select system database)
	public List<TypeCardDto> listTypeCardDtos;


}
