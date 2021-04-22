package com.monetique.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestMerchant implements Serializable {

	private static final long serialVersionUID = 1L;

	Merchant merchant;
	String userId;
	List<String> userIds;
	Date debut;
	Date fin;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public RequestMerchant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestMerchant(List<String> userIds, Date debut, Date fin) {
		super();
		this.userIds = userIds;
		this.debut = debut;
		this.fin = fin;
	}
	
	public RequestMerchant(String userId, Date debut, Date fin) {
		super();
		List<String> l=new ArrayList<>();
		l.add(userId);
		this.userIds = l;
		this.debut = debut;
		this.fin = fin;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}



}
