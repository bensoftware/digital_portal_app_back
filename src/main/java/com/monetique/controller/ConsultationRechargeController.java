package com.monetique.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.Jour;
import com.monetique.dto.RequestDto;
import com.monetique.dto.ResponseClDto;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.TraitementClearingBatchService;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class ConsultationRechargeController {
	
	
	@Autowired
	TraitementClearingBatchService traitementClearingBatchService;;

		@Autowired
	HttpServletResponse  httpServletResponse;
	
	@PreAuthorize("hasAuthority('conscp')")
	@RequestMapping(value="/getConsultationCompense",method=RequestMethod.POST)
	public @ResponseBody ResponseDto getConsultationRechargeStock(@RequestBody Jour req) throws Exception {
		
		ResponseClDto dto=traitementClearingBatchService.getConsultationCompense(req);
					
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);

	}
	
	/*
	
	@PreAuthorize("hasAuthority('irc')")
	@RequestMapping(value="/getConsultationRechargeUtilise",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getConsultationRechargeUtilise() throws Exception {
		
		List<ConsultationRecharge> res=null;
		res=carteUtiliserService.getConsultationRechargeUtilise();
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res);

	}
	
	@PreAuthorize("hasAuthority('rrc')")
	@RequestMapping(value="/getRechercheRecharge",method=RequestMethod.POST)
	public @ResponseBody ResponseDto  getRechercheRecharge(@RequestParam int type,@RequestParam String recherche) throws Exception {
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), carteUtiliserService.getRechercheRecharge(type, recherche));

	}
	
	@PreAuthorize("hasAuthority('exportrech')")
	@RequestMapping(value="/getAllExpiration",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllExpiration() throws Exception {
	
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), carteStockService.getAllExpiration());

	}*/

}
