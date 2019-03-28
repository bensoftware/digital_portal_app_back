package com.monetique.um.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.um.dao.entities.Action;
import com.monetique.um.dto.ResponseDto;
import com.monetique.um.service.IActionService;

@RestController
@Transactional
@CrossOrigin("*")
public class ActionController {
	
	@Autowired
	private IActionService actionService;
	
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	
	/*
	 * methode REST qui retourne la liste de toutes les actions
	 */
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/initActions",method=RequestMethod.GET)
	public  ResponseDto  initListeActionsRest() throws Exception {
	   List<Action>	actions = actionService.getListeAction();
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), actions);

	}
	
	/*
	 * methode Rest qui permet de recuperer une action  à partir de son ID
	 */
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value ="/action/{actionId}", method=RequestMethod.GET)
	public Action findActionById(@PathVariable long actionId) throws Exception {	
		Action action = actionService.findAction(actionId);
		return action;
	}
	
	/*
	 * methode Rest qui permet de créer une action
	 */
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/createAction",method=RequestMethod.POST)
	public boolean createAction(@RequestBody Action action) throws Exception {
		return actionService.createAction(action);
	}
	
	
	/*
	 * methode Rest qui permet de modifier une action
	 */
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/updateAction",method=RequestMethod.PUT)
	public boolean updateAction(@RequestBody Action action) throws Exception {
		return actionService.createAction(action);
	}
	
	/*
	 * methode Rest qui supprimer une action
	 */
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/deleteAction/{idAction}",method=RequestMethod.GET)
	public boolean deleteAction(@PathVariable long idAction) throws Exception {
		return actionService.deleteAction(idAction);
	}
	
}
