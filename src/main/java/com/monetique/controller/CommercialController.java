package com.monetique.controller;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.Client;
import com.monetique.dto.OtpDetails;
import com.monetique.dto.OtpIn;
import com.monetique.dto.OtpOut;
import com.monetique.dto.OtpRequest;
import com.monetique.dto.OtpResponse;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.CommercialService;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.repositories.OtpLogRepository;
import com.monetique.um.dto.ResponseDto;
@RestController
public class CommercialController {
	
	
	@Autowired
	CommercialService commercialService;
	@Autowired
	OtpLogRepository otpLogRepository;
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@PostMapping("/getOtp")
	public OtpDetails getOtp(@RequestBody OtpRequest otpRequest ,HttpServletRequest req)  {
		OtpDetails otpDetails=new OtpDetails();
		OtpLog otpLog=new OtpLog();
		OtpIn otpIn=new OtpIn();
		otpIn.telephone=otpRequest.getTelephone();
		try {
			
			OtpOut otpOut=commercialService.getOtp(otpIn);
			otpDetails.setDateCreationOtp(otpOut.response.dateCreationOtp);
			otpDetails.setOtp(otpOut.response.otp);
			otpDetails.setDateExpirationOtp(otpOut.response.dateExpirationOtp);
			otpDetails.setPartyId(otpOut.response.partyId);
			otpDetails.setTelephone(otpOut.response.telephone);
			
			otpLog.setDate(new Date());
			otpLog.setOtp(otpOut.response.otp);
			otpLog.setPhone(otpRequest.getTelephone());
			otpLog.setUserName(otpRequest.getUserName());
			otpLog.setHost(req.getRemoteHost());
			otpLogRepository.save(otpLog);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return  otpDetails;
	}
	
	
	@RequestMapping(value="/getCifByTelephone",method=RequestMethod.POST)
	public @ResponseBody ResponseDto getConsultationRechargeStock(@RequestParam String telephone) throws Exception {		
		String cif=commercialService.getCifByTelephone(telephone);
					
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), cif);

	}
	
	@PostMapping("/getClientDetails")
	public @ResponseBody Client getClientDetails(@RequestBody OtpRequest req) throws Exception {		
		Client client=null;
		
		
		if("nni".equals(req.getType().trim()))
		 client=commercialService.getClientDetailsByNni(req.getSearched().trim());
		else if ("phone".equals(req.getType().trim()))
			 client=commercialService.getClientDetailsByPhone(req.getSearched().trim());
		else if("cif".equals(req.getType().trim()))
			client=commercialService.getClientDetailsByCif(req.getSearched().trim());
		
			return client;		
		//return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), cif);

	}
}

