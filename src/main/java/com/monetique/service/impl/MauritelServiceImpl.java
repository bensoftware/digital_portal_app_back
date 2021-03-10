package com.monetique.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.SoldeDetails;
import com.monetique.service.MauritelService;



@Service
public class MauritelServiceImpl implements MauritelService{

	@Value("${url.mauritel}")
	String urlMauritel;
	
	@Autowired
	RestTemplate restTemplate;

	
	@Override
	public SoldeDetails getSoldeByLineNumberAndProductType(String lineNumber,String productType) throws Exception {
		
		try {
			//HttpHeaders headers= new HttpHeaders();
			//headers.set("authorization", apiKey);
			
					
			String url= urlMauritel+"/getSoldeByLineNumberAndProductType/"+lineNumber+"/"+productType;
			
			
			ResponseEntity<SoldeDetails> response
			  =restTemplate.getForEntity(new URI(url) , SoldeDetails.class);
		
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				
				SoldeDetails res=response.getBody();
				

				return res;
				
				
			}
			else {
				throw new Exception("Message non envoyer"+response.getStatusCodeValue()); 
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
	
	}

	
	



}
