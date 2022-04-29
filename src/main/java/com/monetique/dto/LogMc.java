package com.monetique.dto;

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
	
}
