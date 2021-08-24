package com.monetique.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.ClientNniDto;
import com.monetique.service.EtatCivilService;

@Service
public class EtatCivilServiceImpl implements EtatCivilService {
       @Autowired
       RestTemplate restTemplate;
       
       @Value("${host.nni}")
       String hostNni;

       
       
       @Override
       public ClientNniDto getInfoNni(String nni) throws Exception {
       
    	   ClientNniDto res=null;

             try {
                    String url= hostNni+"/getInfoNniMobile/"+nni;
                    ResponseEntity<ClientNniDto> response
                      = restTemplate.getForEntity(url, ClientNniDto.class);
                    if(response.getStatusCode().equals(HttpStatus.OK)) {
                           res= response.getBody(); 
                    }
                    
             } catch (Exception e) {
           System.err.println(e);
             }
             
             return res;
             
       }

       
       

}
