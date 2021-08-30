package com.monetique.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.ClientNniDto;
import com.monetique.helper.CorrespondanteCodeHelper;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.EtatCivilService;
import com.monetique.um.dto.ResponseDto;

@RestController
public class EtatCivilController {
	@Autowired
	private EtatCivilService etatCivilService;
	@Autowired
	HttpServletResponse  httpServletResponse;

	@PreAuthorize ("hasAnyAuthority ('apprlb', 'apprlb','users')")
	@RequestMapping(value="/getEtatCivilService/{nni}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getEtatCivilService(@PathVariable String nni) throws Exception {
		nni=CorrespondanteCodeHelper.getNniFormat(nni);
		ClientNniDto dto=etatCivilService.getInfoNni(nni);
		return new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}

}
