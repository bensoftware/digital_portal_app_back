package com.monetique.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry implements Serializable{
private String OperationType;
private String BillNumber;
private String PayerCode;
private String Product;
private String LineNumber;
private String LineSequence;
private String ApplicationDate;
private String BillSequence;
private String Balance;
public String getOperationType() {
	return OperationType;
}
public void setOperationType(String operationType) {
	OperationType = operationType;
}
public String getBillNumber() {
	return BillNumber;
}
public void setBillNumber(String billNumber) {
	BillNumber = billNumber;
}
public String getPayerCode() {
	return PayerCode;
}
public void setPayerCode(String payerCode) {
	PayerCode = payerCode;
}
public String getProduct() {
	return Product;
}
public void setProduct(String product) {
	Product = product;
}
public String getLineNumber() {
	return LineNumber;
}
public void setLineNumber(String lineNumber) {
	LineNumber = lineNumber;
}
public String getLineSequence() {
	return LineSequence;
}
public void setLineSequence(String lineSequence) {
	LineSequence = lineSequence;
}
public String getApplicationDate() {
	return ApplicationDate;
}
public void setApplicationDate(String applicationDate) {
	ApplicationDate = applicationDate;
}
public String getBillSequence() {
	return BillSequence;
}
public void setBillSequence(String billSequence) {
	BillSequence = billSequence;
}
public String getBalance() {
	return Balance;
}
public void setBalance(String balance) {
	Balance = balance;
}
public Entry(String operationType, String billNumber, String payerCode, String product, String lineNumber,
		String lineSequence, String applicationDate, String billSequence, String balance) {
	super();
	OperationType = operationType;
	BillNumber = billNumber;
	PayerCode = payerCode;
	Product = product;
	LineNumber = lineNumber;
	LineSequence = lineSequence;
	ApplicationDate = applicationDate;
	BillSequence = billSequence;
	Balance = balance;
}
public Entry() {
	super();
	// TODO Auto-generated constructor stub
}

}
