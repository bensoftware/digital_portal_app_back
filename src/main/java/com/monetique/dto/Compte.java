package com.monetique.dto;

import java.io.Serializable;

public class Compte implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String glCode;
	String glCodeLabel;
	String status;
	String additionalReference;
	int branchCode;
	int slNo;
	
	
	
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public Compte(String glCode, String glCodeLabel, String status, String additionalReference) {
		super();
		this.glCode = glCode;
		this.glCodeLabel = glCodeLabel;
		this.status = status;
		this.additionalReference = additionalReference;
	}
	public String getGlCodeLabel() {
		return glCodeLabel;
	}
	public void setGlCodeLabel(String glCodeLabel) {
		this.glCodeLabel = glCodeLabel;
	}
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGlCode() {
		return glCode;
	}
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdditionalReference() {
		return additionalReference;
	}
	public void setAdditionalReference(String additionalReference) {
		this.additionalReference = additionalReference;
	}
	
    
	
	

}
