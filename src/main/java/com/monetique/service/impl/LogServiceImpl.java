package com.monetique.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.monetique.dto.LogMc;
import com.monetique.dto.ResponseDtoApi;
import com.monetique.model.helper.LogMcHelper;
import com.monetique.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Value("${url.mc}")
	private String host;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<LogMc> getAllLog() throws Exception {
		String url = host + "/getLogs";

		List<LogMc> listLogMcs=new ArrayList<>();
		
		ResponseEntity<ResponseDtoApi> response = restTemplate.getForEntity(url,ResponseDtoApi.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			if (response.getBody() != null) {
				listLogMcs=LogMcHelper.fromResponseToLogMc(response);
			}else{
				throw new Exception(
						"Aucun mail n'est associé à ce numéro de teléphone selon les resultats dans  SELECT SYSTEM."
						);
			}
		}
		return listLogMcs;
	}

	
	@Override
	public LogMc findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		restTemplate.getForEntity(null, null);
		return null;
	}

	
	@Override
	public List<LogMc> getLogByTwoDate(Date date1, Date date2) throws Exception {
		// TODO Auto-generated method stub
		String url = host+"/getLogByDateAndPhone/"+date1+"/"+date2;
		
		List<LogMc> listLogMcs=new ArrayList<>();
		
		ResponseEntity<ResponseDtoApi> response = restTemplate.getForEntity(url,ResponseDtoApi.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			if (response.getBody() != null) {
				listLogMcs=LogMcHelper.fromResponseToLogMc(response);
			}else{
				throw new Exception(
						"Aucun mail n'est associé à ce numéro de teléphone selon les resultats dans  SELECT SYSTEM."
						);
			}
		}
		return listLogMcs;
	}
	
	
	//Hibernate , Microservices,Java17,Switch,Docker,AMQ,KAFkA

	@Override
	public List<LogMc> getLogByPhoneAndDate( String phone,Date date1, Date date) throws Exception {
		// TODO Auto-generated method stub
		String url = host+"/getLogByDateAndPhone/"+phone+"/"+date1+"/"+date;
		
		List<LogMc> listLogMcs=new ArrayList<>();
		
		ResponseEntity<ResponseDtoApi> response = restTemplate.getForEntity(url,ResponseDtoApi.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			if (response.getBody() != null) {
				listLogMcs=LogMcHelper.fromResponseToLogMc(response);
			}else{
				throw new Exception(
						"Aucun mail n'est associé à ce numéro de teléphone selon les resultats dans  SELECT SYSTEM."
						);
			}
		}
		return listLogMcs;
	}

	
	
	@Override
	public List<LogMc> getByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		String url = host + "/phone";

		List<LogMc> listLogMcs=new ArrayList<>();
		
		ResponseEntity<ResponseDtoApi> response = restTemplate.getForEntity(url,ResponseDtoApi.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			if (response.getBody() != null) {
				listLogMcs=LogMcHelper.fromResponseToLogMc(response);
			}else{
				throw new Exception(
						"Aucun mail n'est associé à ce numéro de teléphone selon les resultats dans  SELECT SYSTEM."
						);
			}
		}
		return listLogMcs;
	}
	

}
