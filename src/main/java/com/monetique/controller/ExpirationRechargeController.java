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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.ExpirationItem;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.CarteStockService;
import com.monetique.service.ExpirationService;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class ExpirationRechargeController {
	
	@Autowired
	CarteStockService carteStockService ;
	
	@Autowired
	ExpirationService expirationService ;
	
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	

	@PreAuthorize("hasAuthority('exportrech')")
	@RequestMapping(value="/getAllExport",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllExport() throws Exception {
	
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), expirationService.getAllExport());

	}
	
	
	@PreAuthorize("hasAuthority('exportrech')")
	@RequestMapping(value="/deleteExpiration",method=RequestMethod.POST)
	public @ResponseBody ResponseDto deleteExpiration(@RequestParam long id) throws Exception {
	
		 expirationService.deleteExpiration(id);;
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('exportrech')")
	@RequestMapping(value="/getExport/{id}",method=RequestMethod.GET)
	public @ResponseBody  ResponseDto  getExport(@PathVariable long id) throws Exception {
	
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),expirationService.getExport(id));

	}
	
	@PreAuthorize("hasAuthority('exportrech')")
	@RequestMapping(value="/valideExport",method=RequestMethod.POST)
	public @ResponseBody  ResponseDto valideExport(@RequestBody ExpirationItem item) throws Exception {
	
		 expirationService.valideExport(item.getIdExpiration(),item.getDateExpiration());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('exportrech')")
	@RequestMapping(value="/saveExport",method=RequestMethod.POST)
	public @ResponseBody  ResponseDto saveExport(@RequestBody ExpirationItem item) throws Exception {
		 expirationService.saveExport(item.getExpirations());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}

}
