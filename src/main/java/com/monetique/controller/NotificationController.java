package com.monetique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.NotificationItem;
import com.monetique.service.IntegrationService;
import com.monetique.service.NotificationService;
import com.monetique.service.OperateurService;

@CrossOrigin("*")
@RestController
public class NotificationController {
	
	@Autowired
	IntegrationService integrationService;
	
	@Autowired
	OperateurService operateurService;
	
	@Autowired
	NotificationService notificationService ;
	
	@RequestMapping(value="/getAllNotificationActive",method=RequestMethod.GET)
	public @ResponseBody List<NotificationItem> getAllNotificationActive() throws Exception {
		return  notificationService.getAllNotificationActive();
	}
	
	@RequestMapping(value="/getNotificationActive/{typeNotification}",method=RequestMethod.GET)
	public @ResponseBody List<NotificationItem> getNotificationActive(@PathVariable int typeNotification) throws Exception {
		return  notificationService.getNotificationActive(typeNotification);
	}
	
	
	@RequestMapping(value="/setNotificationViewed/{typeNotification}",method=RequestMethod.GET)
	public @ResponseBody void setNotificationViewed(@PathVariable int typeNotification) throws Exception {
		  notificationService.setNotificationViewed(typeNotification);
	}
	
	
	
}
