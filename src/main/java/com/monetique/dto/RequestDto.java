package com.monetique.dto;

import java.io.Serializable;
import java.util.Date;

public class RequestDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String userName;
	String actuelPwd;
	String newPwd;
	Date date;
	
	String telephone;
	String cif;
	String addRef;
	String otp;
	String userId;
	String username;
	

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getAddRef() {
		return addRef;
	}

	public void setAddRef(String addRef) {
		this.addRef = addRef;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
