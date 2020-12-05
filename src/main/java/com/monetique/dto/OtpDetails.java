package com.monetique.dto;

import java.util.Date;

public class OtpDetails {
	private String telephone;
	private String partyId;
	private String otp;
	private Date dateCreationOtp;
	private Date dateExpirationOtp;
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Date getDateCreationOtp() {
		return dateCreationOtp;
	}
	public void setDateCreationOtp(Date dateCreationOtp) {
		this.dateCreationOtp = dateCreationOtp;
	}
	public Date getDateExpirationOtp() {
		return dateExpirationOtp;
	}
	public void setDateExpirationOtp(Date dateExpirationOtp) {
		this.dateExpirationOtp = dateExpirationOtp;
	}
	
	
}
