package com.monetique.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseLiaisonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<ItemInfo> infrmationClearing;


	String serviceRequestId;
	String mfsTenantId;
	String serviceFlow;
	String referenceServiceRequestId;
	String userId;
	String responseCode;
	String responseMsg;
	String originalServiceRequestId;
	String status;
	String error;
	public List<ItemInfo> getInfrmationClearing() {
		return infrmationClearing;
	}
	public void setInfrmationClearing(List<ItemInfo> infrmationClearing) {
		this.infrmationClearing = infrmationClearing;
	}
	public String getServiceRequestId() {
		return serviceRequestId;
	}
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public String getMfsTenantId() {
		return mfsTenantId;
	}
	public void setMfsTenantId(String mfsTenantId) {
		this.mfsTenantId = mfsTenantId;
	}
	public String getServiceFlow() {
		return serviceFlow;
	}
	public void setServiceFlow(String serviceFlow) {
		this.serviceFlow = serviceFlow;
	}
	public String getReferenceServiceRequestId() {
		return referenceServiceRequestId;
	}
	public void setReferenceServiceRequestId(String referenceServiceRequestId) {
		this.referenceServiceRequestId = referenceServiceRequestId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public String getOriginalServiceRequestId() {
		return originalServiceRequestId;
	}
	public void setOriginalServiceRequestId(String originalServiceRequestId) {
		this.originalServiceRequestId = originalServiceRequestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
}
