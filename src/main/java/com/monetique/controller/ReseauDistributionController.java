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

import com.monetique.dto.ImalResponse;
import com.monetique.dto.RequestMerchant;
import com.monetique.dto.ResponseMerchant;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.ImalService;
import com.monetique.service.MerchantService;
import com.monetique.um.dto.ResponseDto;

@RestController
@Transactional
@CrossOrigin("*")
public class ReseauDistributionController {
	

	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@Autowired
	MerchantService merchantService;
	
	@Autowired
	ImalService imalService;
	
	@PreAuthorize("hasAuthority('const_cmt_com')")
	@RequestMapping(value="/getCompteBpmByCif/{cif}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getConsultationRechargeStock(@PathVariable String cif) throws Exception {
		
		ImalResponse dto=imalService.getCompteBpmByCif(cif);
					
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);

	}
	
	@PreAuthorize("hasAuthority('genReleve')")
	@RequestMapping(value="/getAllMerchant",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllMerchant() throws Exception {
		
		ResponseMerchant dto=merchantService.getAllMerchant();
					
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);

	}
	
	@PreAuthorize("hasAuthority('genReleve')")
	@RequestMapping(value="/generationReleveMerchant",method=RequestMethod.POST)
	public @ResponseBody ResponseDto generationReleveMerchant(@RequestBody RequestMerchant r) throws Exception {
		
						
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), merchantService.generationReleveMerchant(r));

	}
	

	
	
}

