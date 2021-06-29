package com.monetique.controller;



import java.util.Date;
import java.util.List;

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

import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.CommercialService;
import com.monetique.service.SmsService;
import com.monetique.um.dao.entities.DeblocageLog;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.entities.Superviseur;
import com.monetique.um.dao.entities.Suspicious;
import com.monetique.um.dao.repositories.DeblocageLogRepository;
import com.monetique.um.dao.repositories.OtpLogRepository;
import com.monetique.um.dao.repositories.SuperviseurRepository;
import com.monetique.um.dao.repositories.SuspiciousRepository;
import com.monetique.um.dto.ResponseDto;
import com.monetique.um.helper.CommercialHelper;

@RestController
public class CommercialController {
	
	
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
			otpLog.setType("OTP");

			otpLogRepository.save(otpLog);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return  otpDetails;
	}
	
	
	@RequestMapping(value="/getCifByTelephone",method=RequestMethod.POST)
	public @ResponseBody ResponseDto getCifByTelephone(@RequestParam String telephone) throws Exception {		
		String cif=commercialService.getCifByTelephone(telephone);
					
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), cif);

	}
	
	@PostMapping("/getClientDetails")
	public @ResponseBody Client getClientDetails(@RequestBody OtpRequest req,HttpServletRequest httpReq) throws Exception {		
		Client client=null;
		
		if("nni".equals(req.getType().trim()))
		 client=commercialService.getClientDetailsByNni(req.getSearched().trim());
		else if ("phone".equals(req.getType().trim()))
			 client=commercialService.getClientDetailsByPhone(req.getSearched().trim());
		else if("cif".equals(req.getType().trim()))
			client=commercialService.getClientDetailsByCif(req.getSearched().trim());
		if(client != null) {
			client=checkSuspicious(client,req.getUserName(),httpReq.getRemoteHost());
			client.setSysDate(new Date());
			client.setTemporaire("");          
		}
			
			return client;		
		//return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), cif);

	}
	

	
	@PostMapping("/debloquer")
	public @ResponseBody boolean debloquer(@RequestBody OtpRequest req,HttpServletRequest httpReq) throws Exception {		
		
		
		boolean res =commercialService.deblockByPhone(req.getTelephone());
	
		DeblocageLog deblocageLog =new DeblocageLog();
		deblocageLog.setDate(new Date ());
		deblocageLog.setHost(httpReq.getRemoteHost());
		deblocageLog.setPhone(req.getTelephone());
		deblocageLog.setUserName(req.getUserName());
		
		deblocageLogRepository.save(deblocageLog);
			return res;		
		

	}
	@PostMapping("/resendPin")
	public @ResponseBody boolean resendPin(@RequestBody OtpRequest req,HttpServletRequest httpReq) throws Exception {		
		
		
		String pin =commercialService.resendPinTemporaire(req.getTelephone());
	
		boolean res=pin!=null;
		
		OtpLog otpLog=new OtpLog();
		otpLog.setDate(new Date());
		otpLog.setOtp(pin);
		otpLog.setPhone(req.getTelephone());
		otpLog.setUserName(req.getUserName());
		otpLog.setHost(httpReq.getRemoteHost());
		otpLog.setType("PIN");

		otpLogRepository.save(otpLog);
			return res;		
		

	}
	@PostMapping("/resetAttempts")
	public @ResponseBody boolean resetAttempts(@RequestBody OtpRequest req,HttpServletRequest httpReq) throws Exception {		
		boolean res =commercialService.resetAttempt(req.getTelephone());
	
		OtpLog otpLog=new OtpLog();
		otpLog.setDate(new Date());
		otpLog.setOtp(req.getPinTemp());
		otpLog.setPhone(req.getTelephone());
		otpLog.setUserName(req.getUserName());
		otpLog.setHost(httpReq.getRemoteHost());
		otpLog.setType("TENATATIVE");

		otpLogRepository.save(otpLog);
			return res;		
		

	}
	public  Client checkSuspicious(Client client,String userName,String host){
		boolean exist=false;
		List<Suspicious> suspiciousList;
		
		suspiciousList=suspiciousRepository.findByPhoneAndActiveTrue(client.getTelephone());
		
		if(suspiciousList!=null && suspiciousList.size()>0)
			exist=true;
		else {
			suspiciousList=suspiciousRepository.findByNniAndActiveTrue(client.getNni());
			if(suspiciousList!=null && suspiciousList.size()>0)
				exist=true;
			else {
				suspiciousList=suspiciousRepository.findByCifAndActiveTrue(client.getCif());
				if(suspiciousList!=null && suspiciousList.size()>0)
					exist=true;
			}
		}
		if(exist) {
			client.setStatusId("1");
			client.setStatusLabel("ACTIVE");
			client.setDateExpirationBlocage(null);
			client.setNombreTentative(0);
			client.setLogin(" ");
			
			OtpLog otpLog=new OtpLog();
			otpLog.setDate(new Date());
			otpLog.setPhone(client.getTelephone());
			otpLog.setUserName(userName);
			otpLog.setHost(host);
			otpLog.setType("Consultation-SUSPECT");
			otpLogRepository.save(otpLog);
			
			List<Superviseur> listSuperviseurs =superviseurRepository.findByActiveTrue();
			if(listSuperviseurs!=null && listSuperviseurs.size()>0 ) {
				for(Superviseur superviseur : listSuperviseurs)
					try {
						smsService.sendSms(CommercialHelper.getSuspiciousMessage(client,userName,host), superviseur.getTelephone());
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		return client ;
		
		
	}
}

