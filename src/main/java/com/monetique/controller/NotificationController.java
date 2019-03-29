package com.monetique.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.NotificationItem;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.IntegrationService;
import com.monetique.service.NotificationService;
import com.monetique.service.OperateurService;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class NotificationController {
	
	@Autowired
	IntegrationService integrationService;
	
	@Autowired
	OperateurService operateurService;
	
	@Autowired
	NotificationService notificationService ;
	
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	
	@PreAuthorize("hasAuthority('notrech')")
	@RequestMapping(value="/getAllNotificationActive",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllNotificationActive() throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), notificationService.getAllNotificationActive());

	}
	
	@PreAuthorize("hasAuthority('notrech')")
	@RequestMapping(value="/getNotificationActive/{typeNotification}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto  getNotificationActive(@PathVariable int typeNotification) throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), notificationService.getNotificationActive(typeNotification));

	}
	
	
	@PreAuthorize("hasAuthority('notrech')")
	@RequestMapping(value="/setNotificationViewed/{typeNotification}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto setNotificationViewed(@PathVariable int typeNotification) throws Exception {
		  notificationService.setNotificationViewed(typeNotification);
			return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	
	
}
