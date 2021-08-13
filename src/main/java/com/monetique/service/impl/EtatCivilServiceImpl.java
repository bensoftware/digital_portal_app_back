package com.monetique.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.ClientNni;
import com.monetique.service.EtatCivilService;

@Service
public class EtatCivilServiceImpl implements EtatCivilService {
       @Autowired
       RestTemplate restTemplate;
       
       @Value("${host.nni}")
       String hostNni;

       
       
       @Override
       public ClientNni getInfoNni(String nni) throws Exception {
       
             ClientNni res=null;

             try {
                    String url= hostNni+"/getInfoNni/"+nni;
                    ResponseEntity<ClientNni> response
                      = restTemplate.getForEntity(url, ClientNni.class);
                    if(response.getStatusCode().equals(HttpStatus.OK)) {
                           res= response.getBody(); 
                    }
                    
             } catch (Exception e) {
           System.err.println(e);
             }
             
             return res;
             
       }

       
       

}
