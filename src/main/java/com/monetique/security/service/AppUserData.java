package com.monetique.security.service;

import java.util.Set;

import com.monetique.dto.AuthentificationOut;
import com.monetique.security.entities.AppUser;

public interface AppUserData {

	public AppUser findUserByUsername(String username) throws Exception;
	
	public Object securityUserContext(Object user); 
	
	public boolean CheckPasswordUser(long idUser,String password) throws Exception;
	
	public Set<String> getAllActions(String username,AuthentificationOut out) throws Exception; 
	
	public Set<String> getAllRules(String username) throws Exception; 

	
	public String securePassword(String password); 

	public boolean checkSecurePassword(String password,String passwordEncode); 

}
