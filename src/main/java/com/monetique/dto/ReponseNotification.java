package com.monetique.dto;

import java.io.Serializable;

public class ReponseNotification implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	int erreurCode;
	String erreurMessage;
	Object data;
	
	
	public int getErreurCode() {
		return erreurCode;
	}
	public void setErreurCode(int erreurCode) {
		this.erreurCode = erreurCode;
	}
	public String getErreurMessage() {
		return erreurMessage;
	}
	public void setErreurMessage(String erreurMessage) {
		this.erreurMessage = erreurMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ReponseNotification(int erreurCode, String erreurMessage, Object data) {
		super();
		this.erreurCode = erreurCode;
		this.erreurMessage = erreurMessage;
		this.data = data;
	}
	public ReponseNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
