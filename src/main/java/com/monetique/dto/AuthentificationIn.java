package com.monetique.dto;

import javax.xml.bind.annotation.XmlElement;

public class AuthentificationIn {

	
	@XmlElement (required = true)
	public String apikey;
	
	@XmlElement (required = true)
	public String login;
	
	@XmlElement (required = true)
	public String password;
	
	
}
