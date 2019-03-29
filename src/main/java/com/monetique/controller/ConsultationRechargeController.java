package com.monetique.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.ConsultationRecharge;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.CarteStockService;
import com.monetique.service.CarteUtiliserService;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class ConsultationRechargeController {
	
	@Autowired
	CarteStockService carteStockService ;
	
	@Autowired
	CarteUtiliserService carteUtiliserService ;
		
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@PreAuthorize("hasAuthority('irs')")
	@RequestMapping(value="/getConsultationRechargeStock",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getConsultationRechargeStock() throws Exception {
		
		List<ConsultationRecharge> res=null;

	    res=carteStockService.getConsultationRechargeStock();
			
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), res);

	}
	
	@PreAuthorize("hasAuthority('irc')")
	@RequestMapping(value="/getConsultationRechargeUtilise",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getConsultationRechargeUtilise() throws Exception {
		
		List<ConsultationRecharge> res=null;
		res=carteUtiliserService.getConsultationRechargeUtilise();
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res);

	}
	
	
	@RequestMapping(value="/getRechercheRecharge",method=RequestMethod.POST)
	public @ResponseBody ResponseDto  getRechercheRecharge(@RequestParam int type,@RequestParam String recherche) throws Exception {
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), carteUtiliserService.getRechercheRecharge(type, recherche));

	}
	
	@PreAuthorize("hasAuthority('exportrech')")
	@RequestMapping(value="/getAllExpiration",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllExpiration() throws Exception {
	
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), carteStockService.getAllExpiration());

	}

}
