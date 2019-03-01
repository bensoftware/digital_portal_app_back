package com.monetique.dto;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;


public class InputSendPhoneCardDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement (required = true)
	public String username;
	@XmlElement (required = true)
	public String password;
	@XmlElement (required = true)
	public int operator;
	@XmlElement (required = true)
	public double amount;
	@XmlElement (required = true)
	public String senderPhoneNumber;
	@XmlElement (required = true)
	public String receiverPhoneNumber;
	@XmlElement (required = true)
	public int serverSender;
	
	
}
