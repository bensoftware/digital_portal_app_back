package com.monetique.dto;

import java.io.Serializable;

public class Status implements Serializable{


	private static final long serialVersionUID = 1L;
	
	int codestatus;
	int codeerror;
	
	public int getCodestatus() {
		return codestatus;
	}
	public void setCodestatus(int codestatus) {
		this.codestatus = codestatus;
	}
	public int getCodeerror() {
		return codeerror;
	}
	public void setCodeerror(int codeerror) {
		this.codeerror = codeerror;
	}
	
	
}
