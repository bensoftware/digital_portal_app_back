package com.monetique.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.AuthentificationIn;
import com.monetique.dto.AuthentificationOut;
import com.monetique.service.LdapService;

@Service
public class LdapServiceImpl implements LdapService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${url.ldap}")
	String urlLdap;
	
	@Value("${apiKey}")
	String apiKey;
	


	@Override
	public AuthentificationOut login(AuthentificationIn in) throws Exception {
		// TODO Auto-generated method stub

            try {
                   HttpHeaders headers= new HttpHeaders();
             //      headers.set("authorization", apiKey);
                   

                   in.apikey=apiKey;
                   HttpEntity<AuthentificationIn> requete = new HttpEntity<>(in,headers);
                   
                   String url= urlLdap+"/login";
                   
                   
                   ResponseEntity<AuthentificationOut> response
                     =restTemplate.postForEntity(url, requete, AuthentificationOut.class);
            
                   if(response.getStatusCode().equals(HttpStatus.OK)) {
                          
                	   AuthentificationOut res=response.getBody();
                      
                	   return res;
                          
                          
                          
                          
                   }
                   else {
                          throw new Exception("Message non envoyer ////login"); 
                   }
                   
            } catch (Exception e) {
                   throw new Exception("Message non envoyer ////login"); 
            }
 



	}

}
