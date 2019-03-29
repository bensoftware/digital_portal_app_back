package com.monetique.service;

import com.monetique.entities.SessionApi;

public interface SessionApiService {

	
	public void updatePassword( String actuelPwd, String newPwd) throws Exception;
	
	public SessionApi authenticationSessionApi(String userName, String password) throws Exception;

	public void saveSessionApi(String userName, String password) throws Exception;

	
}
