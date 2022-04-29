package com.monetique.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.monetique.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public int sendSms(String msg, String phone) throws Exception {

		try {
			String url = "http://30.30.1.149/send_sms.php?Phonenumber=" + phone + "&text=" + msg + "&unicode=1";
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return 0;
			}

		} catch (Exception e) {
			return 1;
		}

		return 1;

	}

//	@Override
//	public int sendSmsSuperviseur(String msg,long id) throws Exception {
//
//		
//		
//		List<Superviseur> list= superviseurRepository.getAllSuperviseurByFacturier(id);
//		
//		if(list!=null && list.size()>0) {
//			
//			for(Superviseur x : list) {
//				try {
//					String url= "http://192.168.7.33:8800?Phonenumber="+x.getTelephone()+"&text="+msg;
//					ResponseEntity<Object> response
//					  = restTemplate.getForEntity(url, Object.class);
//					if(response.getStatusCode().equals(HttpStatus.OK)) {
//						//return 0; 
//					}
//					
//				} catch (Exception e) {
//					//return 1;
//				}
//			}
//	
//		}
//		
//		
//		
//		return 1;
//	}

}
