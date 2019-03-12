package com.monetique.dto;

import java.util.Date;

public class IntegrationExcpItem {

String code;
	String description;
	Date dateIntegration;

	
	public Date getDateIntegration() {
		return dateIntegration;
	}
	public void setDateIntegration(Date dateIntegration) {
		this.dateIntegration = dateIntegration;
	}
	public IntegrationExcpItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IntegrationExcpItem(String code, String descrption) {
		super();
		this.code = code;
		this.description = descrption;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public IntegrationExcpItem(String code, String description, Date dateIntegration) {
		super();
		this.code = code;
		this.description = description;
		this.dateIntegration = dateIntegration;
	}
	
	
	
}
