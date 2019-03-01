package com.monetique.dto;


import java.io.Serializable;


public class OutputSendPhoneCardDto implements Serializable{

	private static final long serialVersionUID = 1L;

	String code;
	
	Status status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	
}
