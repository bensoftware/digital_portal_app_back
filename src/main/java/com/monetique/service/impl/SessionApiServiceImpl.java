package com.monetique.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.SessionApi;
import com.monetique.repositories.SessionApiRepository;
import com.monetique.security.service.AppUserData;
import com.monetique.service.SessionApiService;

@Service
public class SessionApiServiceImpl implements SessionApiService {

	
	@Autowired
	AppUserData appUserData;
	
	@Autowired
	SessionApiRepository sessionApiRepository;
	
	
	@Override
	public void updatePassword(String userName,String actuelPwd, String newPwd) throws Exception {
		//recuperer l'userà partir de son login 
		SessionApi user = null;
		
		Optional<SessionApi> optional= sessionApiRepository.findById(userName);
				
		if(!optional.isPresent()) {
			throw new Exception("session Api inexistante");
		}
		
		user=optional.get();
				
	   //vérifier que le mot de passe ancient a été saisie 
	   if (appUserData.checkSecurePassword(actuelPwd, user.getPassword())) {
		// encodage  
			user.setPassword(appUserData.securePassword(newPwd));
            sessionApiRepository.save(user);
	   }
	   else {
			throw new Exception("Le mot de passe  saisie n'est pas correcte. Veuillez réesayez svp");
			
	   }
		//user.setMotdepasseprecedent(user.getMotdepasseprecedent());
		
		
	}


	@Override
	public SessionApi authenticationSessionApi(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		SessionApi sessionApi= sessionApiRepository.findByUsername(userName);
		  
		     if (sessionApi==null) {
					throw new Exception("usernamse inexistante");
			 }
		     
	 boolean isCheck=appUserData.checkSecurePassword(password, sessionApi.getPassword());     
	  
	 if (!isCheck) {
			throw new Exception("mot de passe incorrect");
	 }
	 
	 return sessionApi;
	 
	}


	@Override
	public void saveSessionApi(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		SessionApi api=new SessionApi();
		api.setUsername(userName);
		api.setPassword(appUserData.securePassword(password));
		
		sessionApiRepository.save(api);
	}


}
