package com.monetique.controller;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.OtpDetails;
import com.monetique.dto.OtpIn;
import com.monetique.dto.OtpOut;
import com.monetique.dto.OtpRequest;
import com.monetique.service.OtpService;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.repositories.OtpLogRepository;
@RestController
public class CommercialController {
	
	
	@Autowired
	OtpService otpSrvice;
	@Autowired
	OtpLogRepository otpLogRepository;
	
	@PostMapping("/getOtp")
	public OtpDetails getOtp(@RequestBody OtpRequest otpRequest ,HttpServletRequest req)  {
		OtpDetails otpDetails=new OtpDetails();
		OtpLog otpLog=new OtpLog();
		OtpIn otpIn=new OtpIn();
		otpIn.telephone=otpRequest.getTelephone();
		try {
			
			OtpOut otpOut=otpSrvice.getOtp(otpIn);
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
}

