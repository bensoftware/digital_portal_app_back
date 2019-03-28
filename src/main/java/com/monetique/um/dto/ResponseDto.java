package com.monetique.um.dto;

import java.io.Serializable;

/**
 * Modèle Entité Action Dto.
 *
 * @author bpm digital
 *
 */
public class ResponseDto implements Serializable{

	 private static final long serialVersionUID = 1L;

	 private String token;
	 private Object response;
	 
	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseDto(String token, Object response) {
		super();
		this.token = token;
		this.response = response;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	
	 
	 
}
