package com.monetique.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.monetique.dto.OtpIn;
import com.monetique.dto.OtpOut;
import com.monetique.service.OtpService;
@Service
public class OtpServiceImpl implements OtpService {
	
    String otpUrl="http://30.30.1.140:8836/getOtpByTelephone";
    @Autowired
	RestTemplate restTemplate;
	@Override
	public OtpOut getOtp(OtpIn in) throws Exception {
		
        try {
            HttpHeaders headers= new HttpHeaders();
      //      headers.set("authorization", apiKey);
            

            
            HttpEntity<OtpIn> requete = new HttpEntity<>(in,headers);
            
            String url= otpUrl;
            
            
            ResponseEntity<OtpOut> response
              =restTemplate.postForEntity(url, requete, OtpOut.class);
     
            if(response.getStatusCode().equals(HttpStatus.OK)) {
                   
            	OtpOut res=response.getBody();
               
         	   return res;
                     
            }
            else {
                   throw new Exception("Message non envoyer  /////getOtpByTelephone"); 
            }
            
     } catch (Exception e) {
            throw new Exception("Message non envoyer  /////getOtpByTelephone"); 
     }
	}

}
