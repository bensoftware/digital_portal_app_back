package com.monetique.dto;

public class OtpRequest {
String telephone;
String userName;
String searched;
String type;


public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getSearched() {
	return searched;
}
public void setSearched(String searched) {
	this.searched = searched;
}



}
