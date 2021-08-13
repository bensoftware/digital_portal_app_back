package com.monetique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Liaison {
	private String kycValue;
	private String bearerCode;
	private String languageId;
	private String mobileNumber;
	private String kycIdType;
	private String resumeServiceRequestId;
	public String BankAccId;
	private String isCreateNewAccount;
	public String getKycValue() {
		return kycValue;
	}
	public void setKycValue(String kycValue) {
		this.kycValue = kycValue;
	}
	public String getBearerCode() {
		return bearerCode;
	}
	public void setBearerCode(String bearerCode) {
		this.bearerCode = bearerCode;
	}
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getKycIdType() {
		return kycIdType;
	}
	public void setKycIdType(String kycIdType) {
		this.kycIdType = kycIdType;
	}
	public String getResumeServiceRequestId() {
		return resumeServiceRequestId;
	}
	public void setResumeServiceRequestId(String resumeServiceRequestId) {
		this.resumeServiceRequestId = resumeServiceRequestId;
	}
	public String getIsCreateNewAccount() {
		return isCreateNewAccount;
	}
	public void setIsCreateNewAccount(String isCreateNewAccount) {
		this.isCreateNewAccount = isCreateNewAccount;
	}
	
	
	
}
