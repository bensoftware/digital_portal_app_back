package com.monetique.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.service.IntegrationBatchService;

@Service
public class IntegrationBatchImpl implements IntegrationBatchService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${urlpatch}")
	String urlpatch;
	
	@Override
	public void integrationCleationGIMTELBatch() throws Exception {
		
		// appel http
		restTemplate.postForObject(urlpatch+"/integrationGimtelBatch", null, null);		
			
		
	}

	@Override
	public void integrationCleationSSBatch() {
		// TODO Auto-generated method stub
		
	}

}
