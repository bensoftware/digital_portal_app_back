package com.monetique.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.repositories.EmailNotificationRepository;
import com.monetique.service.MailNotificationService;

@Service
public class MailNotificationServiceImpl implements MailNotificationService {
	
	@Autowired
	EmailNotificationRepository emailNotificationRepository;

	@Override
	public String[] getEmailNotification() throws Exception {
		// TODO Auto-generated method stub
		List<String> list= emailNotificationRepository.getEmailActif();
        
		String[] itemsArray = new String[list.size()];
       return itemsArray = list.toArray(itemsArray);
		
	}
	
	


}
