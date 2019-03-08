package com.monetique.dto;


import java.io.Serializable;


public class ItemInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String code;
	double nbItem=0;
	
	
	
	public ItemInfo(String code, double nbItem) {
		super();
		this.code = code;
		this.nbItem = nbItem;
	}
	public ItemInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getNbItem() {
		return nbItem;
	}
	public void setNbItem(double nbItem) {
		this.nbItem = nbItem;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	
	
	
}
