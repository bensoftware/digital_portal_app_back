package com.monetique.security.service;

import com.monetique.security.entities.AppUser;

public interface AccountService {
	
//	public AppUser saveUser(AppUser user);
//	public AppRole saveRole(AppRole role);
//	public void addRoleToUser(String username,String roleName);
	public AppUser findUserByUsername(String username) throws Exception;	
//	public void majUser(AppUser user);

}
