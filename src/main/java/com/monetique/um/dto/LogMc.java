package com.monetique.um.dto;

import java.util.Date;
import lombok.Data;

@Data
public class LogMc {
	
	private long id;
	private	String  message;
	private String	phone ;
	private Date dateRequest ;
	private	boolean email ;
	private	boolean sms  ;
	private	boolean push ;
	
	
	public LogMc(String message, String phone, Date dateRequest, boolean email, boolean sms, boolean push) {
		super();
		this.message = message;
		this.phone = phone;
		this.dateRequest = dateRequest;
		this.email = email;
		this.sms = sms;
		this.push = push;
	}
	
	
}
