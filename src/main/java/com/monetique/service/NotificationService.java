package com.monetique.service;

import java.util.List;

import com.monetique.dto.ListRequestNotification;
import com.monetique.dto.ReponseNotification;

public interface NotificationService {

	public ReponseNotification sendNotification(ListRequestNotification req) throws Exception;
	
	
	public ReponseNotification sendSms(List<String> telephones,String message) throws Exception;

	
}
