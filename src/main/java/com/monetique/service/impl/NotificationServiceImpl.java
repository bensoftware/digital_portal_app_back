package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.ListRequestNotification;
import com.monetique.dto.ReponseNotification;
import com.monetique.dto.RequestNotification;
import com.monetique.service.NotificationService;


@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${url.notification}")
	String urlNotif;

	@Override
	public ReponseNotification sendNotification(ListRequestNotification req) throws Exception {
		
		try {
			HttpHeaders headers= new HttpHeaders();
			headers.set("authorization", "BPM.BANK");

			HttpEntity<ListRequestNotification> requete = new HttpEntity<>(req,headers);
			
			String url= urlNotif+"/sendNotificationClient";			
			
			ResponseEntity<ReponseNotification> response
			  =restTemplate.postForEntity(url, requete, ReponseNotification.class);
		
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				return response.getBody();
				
			}
			else {
				throw new Exception("Message non envoyer"); 
			}
			
		} catch (Exception e) {
			throw new Exception("Exception d'envoi "+e.getMessage()); 
		}
		
	}

	@Override
	public ReponseNotification sendSms(List<String> telephones, String message) throws Exception {
	
		ListRequestNotification req= new ListRequestNotification();
		req.setType("SMS");
		
		List<RequestNotification> list=new ArrayList<>();
		
		if(telephones!=null && telephones.size()>0)
		for(String x : telephones) {
			list.add(new RequestNotification(message, x, null,null));	
		}
		
		req.setList(list);
		
		try {
			HttpHeaders headers= new HttpHeaders();
			headers.set("authorization", "BPM.BANK");

			HttpEntity<ListRequestNotification> requete = new HttpEntity<>(req,headers);
			
			String url= urlNotif+"/sendNotificationClient";			
			
			ResponseEntity<ReponseNotification> response
			  =restTemplate.postForEntity(url, requete, ReponseNotification.class);
		
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				return response.getBody();
				
			}
			else {
				throw new Exception("Message non envoyer"); 
			}
			
		} catch (Exception e) {
			throw new Exception("Exception d'envoi "+e.getMessage()); 
		}
	}
	
	


}