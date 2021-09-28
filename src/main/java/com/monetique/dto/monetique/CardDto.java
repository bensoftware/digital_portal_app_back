package com.monetique.dto.monetique;

import java.util.Date;

import lombok.Data;

@Data
public class CardDto {
	
	public String pan;
	public String status;
	public TypeCardDto typeCardDto;
	public AccountDtdo accountDtdo;//if the card is not linked the account ,compteDtdo will be null
	public Date dateExpiration;
	
	
	public CardDto(String glCode, String pan, String status, TypeCardDto typeCardDto, AccountDtdo accountDtdo) {
		super();
	
		this.pan = pan;
		this.status = status;
		this.typeCardDto = typeCardDto;
		this.accountDtdo = accountDtdo;
	}

	public CardDto(String glCode, String pan, String status, TypeCardDto typeCardDto) {
		super();
		this.pan = pan;
		this.status = status;
		this.typeCardDto = typeCardDto;
	}

	public CardDto() {
		super();
	}
		
}
