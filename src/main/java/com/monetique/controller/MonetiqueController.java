package com.monetique.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.model.helper.monetique.ClientCifDtoBinded;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.um.dto.ResponseDto;

@RestController
public class MonetiqueController {

	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@Autowired
	ClientCifDtoBinded clientCifDtoBinded;
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/getClientDataByCif/{cif}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getClientDataByCif (@PathVariable String cif) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),ClientCifDtoBinded.getClientCifDto(cif));
	}
	
	
}
