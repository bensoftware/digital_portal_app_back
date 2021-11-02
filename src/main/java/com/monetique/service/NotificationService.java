package com.monetique.service;

import java.util.List;
import com.monetique.dto.ListRequestNotification;
import com.monetique.dto.NotificationMc;
import com.monetique.dto.ReponseNotification;

public interface NotificationService {

	public ReponseNotification sendNotification(ListRequestNotification req) throws Exception;
	
	
	public ReponseNotification sendSms(List<String> telephones,String message) throws Exception;

	public NotificationMc addNotification(NotificationMc notificationMc) throws Exception ;

	public NotificationMc updateNotification(NotificationMc notificationMc) throws Exception ;

	public NotificationMc[] getAllNotifications() throws Exception ;
	

	public int changeEtatNotification(boolean etat,String pan);

	
}
