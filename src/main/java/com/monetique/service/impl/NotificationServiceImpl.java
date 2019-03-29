package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.CarteExport;
import com.monetique.dto.NotificationItem;
import com.monetique.entities.MontantNotification;
import com.monetique.entities.Notification;
import com.monetique.entities.TypeMontant;
import com.monetique.model.helper.CarteHelper;
import com.monetique.model.helper.DateHelper;
import com.monetique.repositories.MontantNotificationRepository;
import com.monetique.repositories.NotificationRepository;
import com.monetique.service.CarteStockService;
import com.monetique.service.MontantService;
import com.monetique.service.NotificationService;
import com.monetique.service.ParametreService;
import com.monetique.um.service.IMailService;

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
	
	@Autowired
	CarteStockService carteStockService ;
	

	@Autowired
	IMailService mailService;

	@Override
	public boolean setNotificationEpuisement(List<MontantNotification> montants) throws Exception {

		// cloturer les notifs > 24
		List<Notification> notifAllActif=notificationRepository.getAllNotificationEpuisementUsed();
		
		List<Notification> notifActive=new ArrayList<>();
		
		if(notifAllActif!=null && notifAllActif.size()!=0)
		for(Notification x : notifAllActif) {
			if(DateHelper.getDelaiStatusNotf(x)) {
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
			if(checkNotif(x,notifActive)==null) {
				// creer une notif
				montantNotifs.add(montNot);
				// notifier par autre API
				mailService.envoyerMailAlertEpuisement(montNot, 1);
			}else {
				MontantNotification mm= checkNotif(x,notifActive);
				mm.setNombre(montNot.getNombre());
				montantNotificationRepository.save(mm);
			}
		}

		
		// creation de la notif 
		if(montantNotifs.size()>0) {
			Notification n= new Notification();
			n.setTypeNotification(1);
			n.setDate(new Date());
			n.setStatus(0);
			n= notificationRepository.save(n);
			
			for(MontantNotification x : montantNotifs) {
				x.setNotification(n);
				montantNotificationRepository.save(x);
			}
			return true;
		}

		
		return false;
	}
	


	private MontantNotification checkNotif(TypeMontant x,List<Notification> list) {
		
		for(Notification n : list) {
			
			//
			List<MontantNotification> lmn=montantNotificationRepository.getMontantNotificationByNotifId(n.getId());
			for(MontantNotification mn : lmn) {
				if(mn.getTypeMontant().getId() ==x.getId()) {
					return mn;
				}
			}
			
		}
		
		return null;
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



	@Override
	public boolean setNotificationExpiration(Map<TypeMontant, Integer> maps) throws Exception {
		
		boolean res=false;
		
		// cloturer les notifs > 24
		List<Notification> notifAllActif=notificationRepository.getAllNotificationExpirationUsed();
	
	   List<Notification> notifActive=new ArrayList<>();
		
		if(notifAllActif!=null && notifAllActif.size()!=0)
		for(Notification x : notifAllActif) {
			if(DateHelper.getDelaiStatusNotf(x)) {
				x.setStatus(2);
				notificationRepository.save(x);
			}else {
				notifActive.add(x);
			}
		}
		
		List<MontantNotification> montantNotifs= new ArrayList<>();

		
		// notifier les expirations
		for(TypeMontant x : maps.keySet()) {
			if(checkNotif(x,notifActive)==null) {
				// creer une notif
				MontantNotification montNot=new MontantNotification(maps.get(x), x, null);
				montantNotifs.add(montNot);
				// notif API
				mailService.envoyerMailAlertEpuisement(montNot, 2);

				
			}else {
				MontantNotification mm= checkNotif(x,notifActive);
				mm.setNombre(maps.get(x));
				montantNotificationRepository.save(mm);
			}
		}
		
		// creation de la notif 
		if(montantNotifs.size()>0) {
			Notification n= new Notification();
			n.setTypeNotification(2);
			n.setDate(new Date());
			n.setStatus(0);
			n= notificationRepository.save(n);
			
			for(MontantNotification x : montantNotifs) {
				x.setNotification(n);
				montantNotificationRepository.save(x);
			}
			return true;
		}
		
		return res;
		
	}



	@Override
	public void checkExpiration() throws Exception {
		
		// get seuil
		int expiration= parametreService.getExpiration();
		
		Map<TypeMontant, Integer> maps=carteStockService.getExpiration(expiration);
		
		if(maps!=null && maps.keySet().size()>0) {
			setNotificationExpiration(maps);
		}
		
		
	}



	@Override
	public List<NotificationItem> getAllNotificationActive() throws Exception {
	
		List<NotificationItem> res= new ArrayList<>();
		
		List<Notification> notifs= notificationRepository.getAllNewNotification();
		
		for(Notification n : notifs) {
			List<MontantNotification> listN= montantNotificationRepository.getMontantNotificationByNotifId(n.getId());
			
			for(MontantNotification  x : listN) {
				
				res.add(new NotificationItem(x.getId(), n.getTypeNotification(), CarteHelper.getTitreNotification(n.getTypeNotification(), (int) x.getTypeMontant().getMontant()), n.getDate(),x.getTypeMontant().getMontant(), x.getNombre(),n.getStatus()));

			}
			
		    
		}
		
		return res;
		
	}





	@Override
	public List<CarteExport> exportAllCarteException(int typeNotification) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<NotificationItem> getNotificationActive(int typeNotification) throws Exception {
		
		List<NotificationItem> res= new ArrayList<>();
		
		List<Notification> notifs=notificationRepository.getAllNotificationUsedByType(typeNotification);
		
		for(Notification n : notifs) {
			List<MontantNotification> listN= montantNotificationRepository.getMontantNotificationByNotifId(n.getId());
			
			for(MontantNotification  x : listN) {
				
				res.add(new NotificationItem(x.getId(), n.getTypeNotification(), null, n.getDate(),x.getTypeMontant().getMontant(), x.getNombre()));

			}
			
		    
		}
		
		return res;
	}



	@Override
	public void setNotificationViewed(int typeNotification) throws Exception {

		List<Notification> notifs=notificationRepository.getAllNotificationUsedByType(typeNotification);
		
		for(Notification n : notifs) {
		 	n.setStatus(1);	
		 	notificationRepository.save(n);
		}
		
	}





}
