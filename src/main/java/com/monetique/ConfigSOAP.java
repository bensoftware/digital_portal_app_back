package com.monetique;

import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.monetique.ws.CardMbankingWS;



@Configuration
public class ConfigSOAP {


	@Value("${soap.server.url}")
	public String url;
	
	@Value("${soap.server.port}")
	public String port;	
	
	@Autowired
	CardMbankingWS cardMbankingWS;
	

	
	@Bean
	public Endpoint getServerPhoneCard() {
		
	 Endpoint endpoint=	Endpoint.publish("http://"+url+":"+port+"/ws/mbanking/recharge",cardMbankingWS);
	 return endpoint;
	
	}
	
	

}
