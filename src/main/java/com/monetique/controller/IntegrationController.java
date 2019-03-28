package com.monetique.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.IntegrationExcpItem;
import com.monetique.dto.ItemInfo;
import com.monetique.entities.IntegrationFile;
import com.monetique.entities.Operateur;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.IntegrationService;
import com.monetique.service.OperateurService;
import com.monetique.um.dao.entities.User;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class IntegrationController {
	
	@Autowired
	IntegrationService integrationService;
	
	@Autowired
	OperateurService operateurService;
	
	@Autowired
	HttpServletResponse  httpServletResponse;
		

	@PreAuthorize("hasAuthority('intvouch')")
	@RequestMapping(value="/getIntegrationVoucher",method=RequestMethod.POST)
	public @ResponseBody ResponseDto  getIntegrationVoucher(@RequestParam int operator,@RequestParam String filename) throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), integrationService.integrationVoucher(operator, filename));

	}
	
	@PreAuthorize("hasAuthority('intvouch')")
	@RequestMapping(value="/getInfoVoucher/{operator}/{filename}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getInfoVoucher(@PathVariable int operator,@PathVariable String filename) throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),integrationService.getInfoVoucher(operator, filename));

	}
	
	@PreAuthorize("hasAuthority('intvouch')")
	@RequestMapping(value="/getOperator/{operator}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getOperator(@PathVariable int operator) throws Exception {

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),operateurService.getOperatorByCode(operator));

	}
	
	
	@PreAuthorize("hasAuthority('intvouch')")
	@RequestMapping(value="/getListVoucher/{operator}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getListVoucher(@PathVariable int operator) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),integrationService.getListVouchers(operator));

	}
	
	@PreAuthorize("hasAuthority('intvouch')")
	@RequestMapping(value="/getHistoriqueVouchers/{operator}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getHistoriqueVouchers(@PathVariable int operator) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),integrationService.getHistoriqueIntegration(operator));

	}
	
	@PreAuthorize("hasAuthority('intvouch')")
	@RequestMapping(value="/getHistoriqueExceptionVouchers/{operator}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getHistoriqueExceptionVouchers(@PathVariable int operator) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),integrationService.getHistoriqueIntegrationException(operator));

	}

	@PreAuthorize("hasAuthority('intvouch')")
	@RequestMapping(value="/getExceptionVouchersByHisp/{id}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getExceptionVouchersByHisp(@PathVariable long id) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),integrationService.getExceptionIntegByHisto(id));

	}
	
	@PreAuthorize("hasAuthority('expint')")
	@RequestMapping(value="/getExceptionByOp/{operator}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getExceptionByOp(@PathVariable int operator) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),integrationService.getExceptionByOp(operator));

	}
}
