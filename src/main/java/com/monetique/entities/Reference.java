package com.monetique.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Reference implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "referenceTransaction", nullable = false)
	String referenceTransaction;
	
	@Column(name = "code_operation", nullable = false)
	String code_operation;

	public String getReferenceTransaction() {
		return referenceTransaction;
	}

	public void setReferenceTransaction(String referenceTransaction) {
		this.referenceTransaction = referenceTransaction;
	}

	public String getCode_operation() {
		return code_operation;
	}

	public void setCode_operation(String code_operation) {
		this.code_operation = code_operation;
	}

	public Reference() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reference(String referenceTransaction, String code_operation) {
		super();
		this.referenceTransaction = referenceTransaction;
		this.code_operation = code_operation;
	}
	
	

}
