package com.monetique.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.RequestDto;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.CommercialService;
import com.monetique.service.MajService;
import com.monetique.service.SmsService;
import com.monetique.um.dao.repositories.DeblocageLogRepository;
import com.monetique.um.dao.repositories.OtpLogRepository;
import com.monetique.um.dao.repositories.SuperviseurRepository;
import com.monetique.um.dao.repositories.SuspiciousRepository;
import com.monetique.um.dto.ResponseDto;

@RestController
public class DigitaleController {
	
	
	@Autowired
	CommercialService commercialService;
	@Autowired
	OtpLogRepository otpLogRepository;
	
	@Autowired 
	DeblocageLogRepository deblocageLogRepository;
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@Autowired
	 SuspiciousRepository suspiciousRepository;
	
	@Autowired
	 SuperviseurRepository superviseurRepository ;
	
	@Autowired
	 SmsService smsService;
	
	@Autowired
	MajService majService;
	
	@PreAuthorize("hasAuthority('majComptBkl')")
	@RequestMapping(value="/getVerify",method=RequestMethod.POST)
	public @ResponseBody ResponseDto getVerify(@RequestBody RequestDto req) throws Exception {	
		
		com.monetique.dto.ResponseDto re=  majService.verify(req);
	
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), re);

	}
	
	@PreAuthorize("hasAuthority('majComptBkl')")
	@RequestMapping(value="/updateCompte",method=RequestMethod.POST)
	public @ResponseBody ResponseDto updateCompte(@RequestBody RequestDto req,HttpServletRequest r) throws Exception {	
		
		com.monetique.dto.ResponseDto re= null;
		try {
			 re=  majService.updateCompteBankily(req,r);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
		
	
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), re);

	}
	
	@PreAuthorize("hasAuthority('majComptBkl')")
	@RequestMapping(value="/getAllHistorique",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getAllHistorique() throws Exception {	
			
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), majService.getAllHistorique());

	}
	
	
	
}

