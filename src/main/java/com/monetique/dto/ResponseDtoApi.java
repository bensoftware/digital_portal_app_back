package com.monetique.dto;

import java.util.List;

public class ResponseDtoApi {

	private List<LogMc> listLogs;

	private String message;


	
	public ResponseDtoApi() {
		super();
	}

	public ResponseDtoApi(List<LogMc> listLogs, String message) {
		super();
		this.listLogs = listLogs;
		this.message = message;
	}

	public List<LogMc> getListLogs() {
		return listLogs;
	}

	public void setListLogs(List<LogMc> listLogs) {
		this.listLogs = listLogs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseDtoApi [listLogs=" + listLogs + ", message=" + message + "]";
	}

	
}
