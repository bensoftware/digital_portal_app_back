package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.MontantNotification;
import com.monetique.entities.Notification;
import com.monetique.entities.TypeMontant;
import com.monetique.model.helper.DateHelper;
import com.monetique.repositories.MontantNotificationRepository;
import com.monetique.repositories.NotificationRepository;
import com.monetique.service.MontantService;
import com.monetique.service.NotificationService;
import com.monetique.service.ParametreService;

@Service
public class NotificationServiceImpl implements NotificationService {

	
	@Autowired
	NotificationRepository notificationRepository ;
	
	@Autowired
	MontantNotificationRepository montantNotificationRepository ;
	
	@Autowired
	ParametreService parametreService ;
	
	@Autowired
	MontantService montantService ;

	@Override
	public boolean setNotificationEpuisement(List<MontantNotification> montants) throws Exception {

		// cloturer les notifs > 24
		List<Notification> notifAllActif=notificationRepository.getAllNotificationUsed();
		
		List<Notification> notifActive=new ArrayList<>();
		
		if(notifAllActif!=null && notifAllActif.size()!=0)
		for(Notification x : notifAllActif) {
			if(DateHelper.getEpuisementDelaiStatusNotf(x)) {
				x.setStatus(2);
				notificationRepository.save(x);
			}else {
				notifActive.add(x);
			}
		}
	
		List<MontantNotification> montantNotifs= new ArrayList<>();
		
		// notifier les epuisement
		for(MontantNotification montNot : montants) {
			TypeMontant x=montNot.getTypeMontant();
			if(!checkNotif(x,notifActive)) {
				// creer une notif
				montantNotifs.add(montNot);
			}
		}

		
		// creation de la notif 
		if(montantNotifs.size()>0) {
			Notification n= new Notification();
			n.setTypeNotification(1);
			n.setDate(new Date());
			n.setStatus(1);
			n= notificationRepository.save(n);
			
			for(MontantNotification x : montantNotifs) {
				x.setNotification(n);
				montantNotificationRepository.save(x);
			}
			return true;
		}

		
		return false;
	}
	


	private boolean checkNotif(TypeMontant x,List<Notification> list) {
		boolean res=false;
		
		for(Notification n : list) {
			
			for(MontantNotification mn : n.getMontants()) {
				if(mn.getTypeMontant().getId() ==x.getId()) {
					return true;
				}
			}
			
		}
		
		return res;
	}



	@Override
	public void checkEpuisement() throws Exception {
		
		// get seuil
		int epuisement= parametreService.getEpuisement();
		
		List<MontantNotification> list=montantService.checkEpuisementMontant(epuisement);
		
		if(list!=null && list.size()>0) {
			setNotificationEpuisement(list);
		}
		
	}

}
