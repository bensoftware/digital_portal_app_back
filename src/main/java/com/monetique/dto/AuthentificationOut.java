package com.monetique.dto;

public class AuthentificationOut {
	
	//@XmlElement (required = true)
	//@XmlJavaTypeAdapter(BigdecimalXmlAdapter.class)
	public String errorMessage;
	public int errorCode;
	public UserDetails data;
	
}
