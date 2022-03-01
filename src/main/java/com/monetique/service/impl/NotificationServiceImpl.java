package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.ListRequestNotification;
import com.monetique.dto.NotificationMc;
import com.monetique.dto.ReponseNotification;
import com.monetique.dto.RequestNotification;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.NotificationService;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.repositories.OtpLogRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${url.notification}")
	String urlNotif;
	
	@Value("${host.notification}")
	String hostNotif;
	
    @Autowired
	HttpServletRequest request;
	
    @Autowired
	OtpLogRepository otpLogRepository;

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

	@Override
	public NotificationMc addNotification(NotificationMc notificationMc) throws Exception {
		
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		String username=claims.getSubject();

		HttpHeaders headers= new HttpHeaders();
		headers.set("utilisateur", username);

		HttpEntity<NotificationMc> requete = new HttpEntity<>(notificationMc,headers);
		
	
		
		NotificationMc res= null;
		String url= hostNotif+"/addClient";
		ResponseEntity<NotificationMc> response = restTemplate.postForEntity(url, requete, NotificationMc.class) ;
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			res= response.getBody(); 	
			// log
			OtpLog otpLog=new OtpLog();
	   		otpLog.setDate(new Date());
			otpLog.setUserName(username);
			otpLog.setHost(request.getRemoteHost());
			otpLog.setType("ADD ALERT_SMS_NOTIFICATION_MC");

			otpLogRepository.save(otpLog);
		}		
		return res;
	}

	@Override
	public NotificationMc updateNotification(NotificationMc notificationMc) throws Exception {
		
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		String username=claims.getSubject();
		
		HttpHeaders headers= new HttpHeaders();
		headers.set("utilisateur", username);

		HttpEntity<NotificationMc> requete = new HttpEntity<>(notificationMc,headers);
		
		
		NotificationMc res= null;
		String url= hostNotif+"/updateClient";
		ResponseEntity<NotificationMc> response = restTemplate.postForEntity(url, requete, NotificationMc.class) ;
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			res= response.getBody(); 
			// log
			OtpLog otpLog=new OtpLog();
	   		otpLog.setDate(new Date());
			otpLog.setUserName(username);
			otpLog.setHost(request.getRemoteHost());
			otpLog.setType("UPDATE ALERT_SMS_NOTIFICATION_MC");

			otpLogRepository.save(otpLog);
		}		
		return res;
	}

	@Override
	public NotificationMc[] getAllNotifications() throws Exception {
	    NotificationMc[] res=null;
		String url= hostNotif+"/getAllClient";
		ResponseEntity<NotificationMc[]> response = restTemplate.getForEntity(url, NotificationMc[].class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		return res;
	}

	@Override
	public int changeEtatNotification(boolean etat, String pan) {
		
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		String username=claims.getSubject();
		
		HttpHeaders headers= new HttpHeaders();
		headers.set("utilisateur", username);

		HttpEntity<Void> requete = new HttpEntity<>(headers);
		
		try {
			String url= hostNotif+"/changeEtat/"+etat+"/"+pan;
			ResponseEntity<Void> response
			  = restTemplate.exchange(url, HttpMethod.GET, requete, Void.class); 
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				
				// log
				OtpLog otpLog=new OtpLog();
		   		otpLog.setDate(new Date());
				otpLog.setUserName(username);
				otpLog.setHost(request.getRemoteHost());
				otpLog.setType("CHANGE_ETAT ALERT_SMS_NOTIFICATION_MC");

				otpLogRepository.save(otpLog);
				
				
				return 1;
			}
			
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	@Override
	public void deleteNotification(String pan) {
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		String username=claims.getSubject();
		
		HttpHeaders headers= new HttpHeaders();
		headers.set("utilisateur", username);

		HttpEntity<Void> requete = new HttpEntity<>(headers);
		
		try {
			String url= hostNotif+"/deleteClient/"+pan;
			ResponseEntity<Void> response
			  = restTemplate.exchange(url, HttpMethod.GET, requete, Void.class); 
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				// log
				OtpLog otpLog=new OtpLog();
		   		otpLog.setDate(new Date());
				otpLog.setUserName(username);
				otpLog.setHost(request.getRemoteHost());
				otpLog.setType("DELETE ALERT_SMS_NOTIFICATION_MC");

				otpLogRepository.save(otpLog);
			}
			
		} catch (Exception e) {
		}
	}
	
	


}
