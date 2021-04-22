package com.monetique.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.RequestMerchant;
import com.monetique.dto.ResponseMerchant;
import com.monetique.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	
	@Autowired
	RestTemplate restTemplate;
	

	@Value("${host.merchant.service}")
	String hostMerchantService;
	

	@Override
	public ResponseMerchant getAllMerchant() throws Exception {
        
		try {
			
			HttpHeaders headers= new HttpHeaders();
			headers.set("authorization", "Bpm@123456");
			
	        HttpEntity<String> entete = new HttpEntity<String>("parameters", headers);

			
			String url= hostMerchantService+"/getAllMerchant";

            ResponseEntity<ResponseMerchant> response = restTemplate.exchange(url, HttpMethod.GET, entete, ResponseMerchant.class);
			
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				ResponseMerchant res = response.getBody();

				return res;
			}
			else {
				throw new Exception("erreur survenu"); 
			}
					
			
		 } catch (Exception e) {
			 throw new Exception("erreur survenu");
		 }
		
	}




	@Override
	public ResponseMerchant generationReleveMerchant(RequestMerchant req) throws Exception {
		try {
			HttpHeaders headers= new HttpHeaders();
			headers.set("authorization", "Bpm@123456");

			HttpEntity<RequestMerchant> requete = new HttpEntity<>(req,headers);
			
			String url= hostMerchantService+"/generationReleveMerchant";
			
			
			ResponseEntity<ResponseMerchant> response
			  =restTemplate.postForEntity(url, requete, ResponseMerchant.class);
		
			
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				
				return  response.getBody();
			}
			else {
				throw new Exception("erreur de appel"); 
			}
			
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}


}
