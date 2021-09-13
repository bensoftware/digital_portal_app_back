package com.monetique.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.model.helper.monetique.ClientCifDtoBinded;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.MonetiqueService;
import com.monetique.um.dto.ResponseDto;

@RestController
@CrossOrigin("*")
public class MonetiqueController {
    
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@Autowired
	ClientCifDtoBinded clientCifDtoBinded;
	
	@Autowired
	MonetiqueService monetiqueService;
	
	@PreAuthorize("hasAuthority('createcardmon')")
	@RequestMapping(value="/getClientDataByCif/{cif}", produces ={"application/json"},method=RequestMethod.GET)
	public @ResponseBody ResponseDto getClientDataByCif (@PathVariable String cif) throws Exception {
		//test cif validations format
		ClientCifDto res=null;
		try {
			res=monetiqueService.getClientDataByCif(cif);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res);
	}
	
	
	@PreAuthorize("hasAuthority('createcardmon')")
	@RequestMapping(value="/test",method=RequestMethod.POST)
	public @ResponseBody ResponseDto test(@RequestBody ClientCifDto clientCifDto) throws Exception {
		//test cif validations format
		ClientCifDto res=null;
		try {
			res=monetiqueService.getClientDataByCif(clientCifDto.getCif());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res);
	}
	
	
	@PreAuthorize("hasAuthority('createcardmon')")
	@RequestMapping(value="/createCarte",method=RequestMethod.POST)
	public @ResponseBody ResponseDto createCarte(@RequestBody ClientCifDto clientCifDto) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),monetiqueService.createCarte(clientCifDto));
	}
	
}
