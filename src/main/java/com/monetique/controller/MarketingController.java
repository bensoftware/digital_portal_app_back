package com.monetique.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.ApiMauritelIn;
import com.monetique.dto.MauritelClient;

import com.monetique.dto.OtpRequest;
import com.monetique.dto.SoldeDetails;
import com.monetique.service.MarketingService;
import com.monetique.service.MauritelService;


@RestController
public class MarketingController {
	@Autowired
	MarketingService marketingService;
	
	@Autowired
	MauritelService mauritelService ;
	
	
	@PostMapping("/getMauritelClientDetailsByLineNumber")
	public MauritelClient getMauritelClientDetailsByLineNumber(@RequestBody OtpRequest otpRequest ,HttpServletRequest req)  {
		
		ApiMauritelIn apiMauritelIn=new ApiMauritelIn();
		apiMauritelIn.setLineNumber(otpRequest.getLineNumber());
		System.out.println(otpRequest.getLineNumber());
		
		apiMauritelIn.setLineNumber(otpRequest.getLineNumber());
		
		MauritelClient mauritelClient=new MauritelClient();
		try {
			 SoldeDetails soldeDetails= mauritelService.getSoldeByLineNumberAndProductType(otpRequest.getLineNumber(), "TPH");
			if(soldeDetails!=null) {
				mauritelClient.setCustCode(soldeDetails.getClientCode());
				mauritelClient.setName(soldeDetails.getClientName());
				
			}
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return  mauritelClient;
	}
}
