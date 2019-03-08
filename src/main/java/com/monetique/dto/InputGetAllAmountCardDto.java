package com.monetique.dto;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;


public class InputGetAllAmountCardDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement (required = true)
	public String username;
	@XmlElement (required = true)
	public String password;
	@XmlElement (required = true)
	public int operator;
	
}
