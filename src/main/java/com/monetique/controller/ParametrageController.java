package com.monetique.controller;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.ItemTypeRecharge;
import com.monetique.entities.Parametrage;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.MontantService;
import com.monetique.service.ParametreService;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class ParametrageController {
	

	
	@Autowired
	ParametreService parametreService ;
	
	@Autowired
	MontantService  montantService ;
	
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	
	
	
	@PreAuthorize("hasAuthority('parglo')")
	@RequestMapping(value="/getParameter",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getParameter() throws Exception {
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), parametreService.getParameter());

	}
	
	@PreAuthorize("hasAuthority('parglo')")
	@RequestMapping(value="/setParameter",method=RequestMethod.POST)
	public @ResponseBody  ResponseDto setParameter(@RequestBody Parametrage p) throws Exception {
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), parametreService.setParameter(p));

	}
	
	
	@PreAuthorize("hasAuthority('parrech')")
	@RequestMapping(value="/getAllRecharge",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllRecharge() throws Exception {
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), montantService.getAllRecharge());

	}
	
	@PreAuthorize("hasAuthority('parrech')")
	@RequestMapping(value="/getTypeRecharge/{id}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getTypeRecharge(@PathVariable long id) throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), montantService.getTypeRecharge(id));

	}
	
	@PreAuthorize("hasAuthority('parrech')")
	@RequestMapping(value="/setTypeRecharge",method=RequestMethod.POST)
	public @ResponseBody ResponseDto setTypeRecharge(@RequestBody ItemTypeRecharge c) throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), montantService.setTypeRecharge(c));

	}
	
	
}
