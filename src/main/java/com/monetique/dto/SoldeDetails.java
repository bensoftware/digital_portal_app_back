package com.monetique.dto;

public class SoldeDetails {
private String clientCode;
private String Solde;
private String payerCode;
private String clientName ;
private Entry entry;
private String result;
private String payerName;

public String getPayerName() {
	return payerName;
}
public void setPayerName(String payerName) {
	this.payerName = payerName;
}
public Entry getEntry() {
	return entry;
}
public void setEntry(Entry entry) {
	this.entry = entry;
}
public String getClientCode() {
	return clientCode;
}
public void setClientCode(String clientCode) {
	this.clientCode = clientCode;
}
public String getSolde() {
	return Solde;
}
public void setSolde(String solde) {
	Solde = solde;
}
public String getPayerCode() {
	return payerCode;
}
public void setPayerCode(String payerCode) {
	this.payerCode = payerCode;
}
public String getClientName() {
	return clientName;
}
public void setClientName(String clientName) {
	this.clientName = clientName;
}
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}

}
