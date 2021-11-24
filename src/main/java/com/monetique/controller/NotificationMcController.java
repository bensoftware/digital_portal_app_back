package com.monetique.controller;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.dto.NotificationMc;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.NotificationService;
import com.monetique.um.dto.ResponseDto;

@RestController
public class NotificationMcController {
	@Autowired
	private NotificationService notificationService;

	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@PreAuthorize("hasAuthority('notificationmc')")
	@RequestMapping(value="/addNotification",method=RequestMethod.POST)
	public @ResponseBody ResponseDto addNotification(@RequestBody NotificationMc r) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), notificationService.addNotification(r));
	}
	
	@PreAuthorize("hasAuthority('notificationmc')")
	@RequestMapping(value="/updateNotification",method=RequestMethod.POST)
	public @ResponseBody ResponseDto updateNotification(@RequestBody NotificationMc r) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), notificationService.updateNotification(r));
	}
	
	@PreAuthorize("hasAuthority('notificationmc')")
	@RequestMapping(value="/getAllNotifications",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllNotifications() throws Exception {
		NotificationMc[] dto=notificationService.getAllNotifications();
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	
	@PreAuthorize("hasAuthority('notificationmc')")
	@RequestMapping(value="/changeEtatNotification/{etat}/{pan}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto changeEtatNotification(@PathVariable boolean etat,@PathVariable String pan) throws Exception {
		notificationService.changeEtatNotification(etat, pan);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
	}
	
	@PreAuthorize("hasAuthority('notificationmc')")
	@RequestMapping(value="/deleteNotification/{pan}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto deleteNotification(@PathVariable String pan) throws Exception {
		notificationService.deleteNotification(pan);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
	}

}
