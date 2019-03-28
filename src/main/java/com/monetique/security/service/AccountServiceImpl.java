package com.monetique.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monetique.security.entities.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	AppUserData appUserData;
	
	@Override
	public AppUser findUserByUsername(String username) throws Exception {
		
		return appUserData.findUserByUsername(username);
	}
	
/*
	
	@Override
	public AppUser saveUser(AppUser user) {
		
		String hashPw=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPw);
		return appUserRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		
		return appRoleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
	
		AppRole role=appRoleRepository.findByRoleName(roleName);
		AppUser user =appUserRepository.findByUsername(username);
		user.getRoles().add(role);
		appUserRepository.saveAndFlush(user);
		
	}



	@Override
	public void majUser(AppUser user) {
		
		appUserRepository.saveAndFlush(user);
		
	}

*/
}
