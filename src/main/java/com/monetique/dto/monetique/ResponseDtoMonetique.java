package com.monetique.dto.monetique;

import lombok.Data;

@Data
public class ResponseDtoMonetique {

	 private static final long serialVersionUID = 1L;

	 private String token;
	 private ClientCifDto response;
	 
	 
	 
	public ResponseDtoMonetique(String token, ClientCifDto response) {
		super();
		this.token = token;
		this.response = response;
	}



	public ResponseDtoMonetique() {
		super();
	}
	 
	 
	 
}
