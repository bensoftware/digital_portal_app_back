package com.monetique.security.entities;

import java.util.HashSet;
import java.util.Set;

public class Actions {
  
	
	Set<Object> actions=new HashSet<>();

	public Set<Object> getActions() {
		return actions;
	}

	public void setActions(Set<Object> actions) {
		this.actions = actions;
	}

	public Actions(Set<Object> actions) {
		super();
		this.actions = actions;
	}

	public Actions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
   
   
}
