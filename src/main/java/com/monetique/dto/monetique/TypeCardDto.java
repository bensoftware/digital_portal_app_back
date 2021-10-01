package com.monetique.dto.monetique;

import lombok.Data;

@Data
public class TypeCardDto {
	
	public String id ;
	public String code ;
	public String libelle;
	

	public TypeCardDto() {
		super();
	}

	public TypeCardDto(String id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	
}
