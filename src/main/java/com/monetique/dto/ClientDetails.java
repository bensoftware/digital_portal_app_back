package com.monetique.dto;

import java.util.Date;

public class ClientDetails {
private String lineNumber;
private String productType;
private String custCode;
private String payerCode;
private String lineSequence;
private Date applicationDate;
private String billSequence;
private String amount;
private Date date;

public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getBillSequence() {
	return billSequence;
}
public void setBillSequence(String billSequence) {
	this.billSequence = billSequence;
}
public String getLineNumber() {
	return lineNumber;
}
public void setLineNumber(String lineNumber) {
	this.lineNumber = lineNumber;
}
public String getProductType() {
	return productType;
}
public void setProductType(String productType) {
	this.productType = productType;
}
public String getCustCode() {
	return custCode;
}
public void setCustCode(String custCode) {
	this.custCode = custCode;
}
public String getPayerCode() {
	return payerCode;
}
public void setPayerCode(String payerCode) {
	this.payerCode = payerCode;
}
public String getLineSequence() {
	return lineSequence;
}
public void setLineSequence(String lineSequence) {
	this.lineSequence = lineSequence;
}
public Date getApplicationDate() {
	return applicationDate;
}
public void setApplicationDate(Date applicationDate) {
	this.applicationDate = applicationDate;
}

}
