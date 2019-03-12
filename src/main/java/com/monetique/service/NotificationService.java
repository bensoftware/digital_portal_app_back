package com.monetique.service;

import java.util.List;

import com.monetique.entities.MontantNotification;

public interface NotificationService {

	public void checkEpuisement() throws Exception;
	
	public boolean setNotificationEpuisement(List<MontantNotification> montants) throws Exception;
}
