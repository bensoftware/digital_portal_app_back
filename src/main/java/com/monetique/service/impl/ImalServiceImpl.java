package com.monetique.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.ImalResponse;
import com.monetique.service.ImalService;
@Service
public class ImalServiceImpl implements ImalService {
	
    
    @Value("${host.imal}")
	String urlImal;
    
    @Autowired
    RestTemplate restTemplate;

	@Override
	public ImalResponse getCompteBpmByCif(String cif) throws Exception {
		ImalResponse res=null;
		
		String url= urlImal+"/getCompteBpmByCif/"+cif;
		ResponseEntity<ImalResponse> response
		  = restTemplate.getForEntity(url, ImalResponse.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		
		return res;
	}
	
    
}
