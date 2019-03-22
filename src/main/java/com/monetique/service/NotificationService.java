package com.monetique.service;

import java.util.List;
import java.util.Map;

import com.monetique.dto.CarteExport;
import com.monetique.dto.NotificationItem;
import com.monetique.entities.MontantNotification;
import com.monetique.entities.TypeMontant;

public interface NotificationService {

	public List<NotificationItem> getAllNotificationActive() throws Exception;
	public List<NotificationItem> getNotificationActive(int typeNotification) throws Exception;
	public void setNotificationViewed(int typeNotification) throws Exception;
	
	public List<CarteExport> exportAllCarteException(int typeNotification) throws Exception;

	public void checkExpiration() throws Exception;
	public boolean setNotificationExpiration(Map<TypeMontant, Integer> maps) throws Exception;
	public void checkEpuisement() throws Exception;
	public boolean setNotificationEpuisement(List<MontantNotification> montants) throws Exception;
}
