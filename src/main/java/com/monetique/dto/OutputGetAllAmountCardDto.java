package com.monetique.dto;


import java.io.Serializable;
import java.util.List;


public class OutputGetAllAmountCardDto implements Serializable{

	private static final long serialVersionUID = 1L;

	List<Double> amounts;
	
	Status status;

	

	public List<Double> getAmounts() {
		return amounts;
	}

	public void setAmounts(List<Double> amounts) {
		this.amounts = amounts;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	
}
