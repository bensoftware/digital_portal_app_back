package com.monetique.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseMerchant implements Serializable {

	private static final long serialVersionUID = 1L;

	String generationUrl;
	
List<Merchant> merchants;

List<PaiementMerchant> paiementMerchants;



public String getGenerationUrl() {
	return generationUrl;
}

public void setGenerationUrl(String generationUrl) {
	this.generationUrl = generationUrl;
}

public List<Merchant> getMerchants() {
	return merchants;
}

public void setMerchants(List<Merchant> merchants) {
	this.merchants = merchants;
}

public List<PaiementMerchant> getPaiementMerchants() {
	return paiementMerchants;
}

public void setPaiementMerchants(List<PaiementMerchant> paiementMerchants) {
	this.paiementMerchants = paiementMerchants;
}




}
