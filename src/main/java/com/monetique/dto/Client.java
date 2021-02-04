package com.monetique.dto;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String telephone;
	String login;
	String partyId;
	String nni;
	Date date;
	String cif;
	
	private String firstName;
	private String lastName;
	private String picture;
	private String kyc_label;
	private String kycStatusId;
	private String statusLabel;
	private String statusId;
	private String bearer ;
	
	
	public String getBearer() {
		return bearer;
	}
	public void setBearer(String bearer) {
		this.bearer = bearer;
	}
	public String getNni() {
		return nni;
	}
	public void setNni(String nni) {
		this.nni = nni;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String telephone, String login) {
		super();
		this.telephone = telephone;
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getKyc_label() {
		return kyc_label;
	}
	public void setKyc_label(String kyc_label) {
		this.kyc_label = kyc_label;
	}
	public String getKycStatusId() {
		return kycStatusId;
	}
	public void setKycStatusId(String kycStatusId) {
		this.kycStatusId = kycStatusId;
	}
	public String getStatusLabel() {
		return statusLabel;
	}
	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	
	
}
