package com.monetique.security.entities;

public class AppRole {
   
   long id;
   String roleName;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public AppRole(String roleName) {
	super();
	this.roleName = roleName;
}
public AppRole() {
	super();
	// TODO Auto-generated constructor stub
}

   
   
   
}
