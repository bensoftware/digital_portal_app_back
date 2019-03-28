package com.monetique.security.beans;

import java.io.Serializable;

public class ResgisterForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username,password,repassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	

}
