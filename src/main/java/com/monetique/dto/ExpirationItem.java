package com.monetique.dto;


import java.io.Serializable;
import java.util.List;


public class ExpirationItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<CarteExport> expirations;
	long idExpiration;
    String dateExpiration;
	public List<CarteExport> getExpirations() {
		return expirations;
	}
	public void setExpirations(List<CarteExport> expirations) {
		this.expirations = expirations;
	}
	public long getIdExpiration() {
		return idExpiration;
	}
	public void setIdExpiration(long idExpiration) {
		this.idExpiration = idExpiration;
	}
	public String getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
    
    
	
}
