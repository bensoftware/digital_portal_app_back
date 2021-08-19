package com.monetique.dto;

import java.io.Serializable;
import java.util.List;

public class ListRequestNotification implements Serializable {

	private static final long serialVersionUID = 1L;
	
List<RequestNotification> list;
String type; // SMS

public List<RequestNotification> getList() {
	return list;
}

public void setList(List<RequestNotification> list) {
	this.list = list;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}



}
