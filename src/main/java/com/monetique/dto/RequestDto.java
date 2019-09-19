package com.monetique.dto;

import java.io.Serializable;
import java.util.Date;

public class RequestDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String userName;
	String actuelPwd;
	String newPwd;
	Date date;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActuelPwd() {
		return actuelPwd;
	}

	public void setActuelPwd(String actuelPwd) {
		this.actuelPwd = actuelPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

	

}
