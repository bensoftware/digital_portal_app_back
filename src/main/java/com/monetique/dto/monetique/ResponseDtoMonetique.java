package com.monetique.dto.monetique;

import lombok.Data;

@Data
public class ResponseDtoMonetique {

	 private static final long serialVersionUID = 1L;

		private ClientCifDto data;
		private int errorCode;
	    private String errorMessage;
	 
	 
	 
}
