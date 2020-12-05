package com.monetique.service;

import com.monetique.dto.AuthentificationIn;
import com.monetique.dto.AuthentificationOut;

public interface LdapService {

	
	public AuthentificationOut login(AuthentificationIn in) throws Exception ; 
	
	
}
