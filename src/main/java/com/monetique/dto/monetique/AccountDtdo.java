package com.monetique.dto.monetique;

import lombok.Data;

@Data
public class AccountDtdo {
	public String additionalReference;
	public String agence;
	public String devise;
	public String glCode;
	public String cif;
	public String slNo;
	public String num;
	
	
	public AccountDtdo(String additionalReference, String agence, String devise, String glCode, String cif, String slNo,
			String num) {
		
		this.additionalReference = additionalReference;
		this.agence = agence;
		this.devise = devise;
		this.glCode = glCode;
		this.cif = cif;
		this.slNo = slNo;
		this.num = num;
	}


	public AccountDtdo() {
		super();
	}
	
	
}
