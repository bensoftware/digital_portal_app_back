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

import com.monetique.dto.ResponseClDto;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.TraitementClearingBatchService;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class IntegrationController {
/*	
	@Autowired
	IntegrationService integrationService;*/
	

	@Autowired
	TraitementClearingBatchService clearingBatchService;
	
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@PreAuthorize("hasAuthority('intclg')")
	@RequestMapping(value="/getIntegrationClearing",method=RequestMethod.POST)
	public @ResponseBody ResponseDto  getIntegrationClearing(@RequestParam String filename) throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), clearingBatchService.getIntegClearingFiles(filename));

	}
	
		
	@PreAuthorize("hasAuthority('intclg')")
	@RequestMapping(value="/getListPreIntGimtel",method=RequestMethod.GET)
	public  @ResponseBody ResponseDto  getListPreIntGimtel() throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),clearingBatchService.getListClearingFiles());

	}
	
	@PreAuthorize("hasAuthority('intclg')")
	@RequestMapping(value="/getOperarionPreIntClearingGimtel/{filename}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto  getOperarionPreIntClearingGimtel(@PathVariable String filename) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), clearingBatchService.preIntClearingByFileName(filename));

	}

	/*@PreAuthorize("hasAuthority('intclg')")
	@RequestMapping(value="/getAllIntegrationClGimtel",method=RequestMethod.POST)
	public @ResponseBody ResponseDto  getAllIntegrationClGimtel() throws Exception {

		integrationService.integrationClGimtel();
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);

	}
	*/
	

}
