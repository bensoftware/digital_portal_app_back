package com.monetique.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class RequestExpiration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long idExpiration;
	Date dateExpiration;
	List<CarteExport> list;
	public long getIdExpiration() {
		return idExpiration;
	}
	public void setIdExpiration(long idExpiration) {
		this.idExpiration = idExpiration;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public List<CarteExport> getList() {
		return list;
	}
	public void setList(List<CarteExport> list) {
		this.list = list;
	}
	
	
	
	
}
