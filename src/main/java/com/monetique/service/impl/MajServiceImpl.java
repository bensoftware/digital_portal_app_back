package com.monetique.service.impl;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.Historique;
import com.monetique.dto.RequestDto;
import com.monetique.dto.ResponseDto;
import com.monetique.service.MajService;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.repositories.OtpLogRepository;

@Service
public class MajServiceImpl implements MajService {
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${url.maj.bankily}")
	String urlMajBankily;
	

	@Autowired
	OtpLogRepository logRepository;
	
	@Override
	public List<Historique> getAllHistorique() throws Exception {

		try {
			
			HttpHeaders headers= new HttpHeaders();
			headers.set("authorization", "Bpm@123456!");
			
	        HttpEntity<String> entete = new HttpEntity<String>("parameters", headers);

			
			String url= urlMajBankily+"/api/getAllHistorique";

            ResponseEntity<ResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entete, ResponseDto.class);
			
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				ResponseDto res = response.getBody();
				return res.getHistoriques();
			}
			else {
				throw new Exception("erreur survenu"); 
			}
					
			
		 } catch (Exception e) {
			 throw new Exception("erreur survenu "+e.getMessage());
		 }
	}

	@Override
	public ResponseDto verify(RequestDto req) throws Exception {
		
		try {
			HttpHeaders headers= new HttpHeaders();
			headers.set("authorization", "Bpm@123456!");

			HttpEntity<RequestDto> requete = new HttpEntity<>(req,headers);
			
			String url= urlMajBankily+"/api/verify";
			
			
			ResponseEntity<ResponseDto> response
			  =restTemplate.postForEntity(url, requete, ResponseDto.class);
		
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				return response.getBody();
			}
			else {
				throw new Exception("erreur de appel"); 
			}
			
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}



	@Override
	public ResponseDto updateCompteBankily(RequestDto request,HttpServletRequest req) throws Exception {
		try {
			HttpHeaders headers= new HttpHeaders();
			headers.set("authorization", "Bpm@123456!");

			HttpEntity<RequestDto> requete = new HttpEntity<>(request,headers);
			
			String url= urlMajBankily+"/api/updateCompteBankily";
			
			
			ResponseEntity<ResponseDto> response
			  =restTemplate.postForEntity(url, requete, ResponseDto.class);
		
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				
				//
				OtpLog otpLog=new OtpLog();
				
				otpLog.setDate(new Date());
				otpLog.setUserName(request.getUsername());
				otpLog.setHost(req.getRemoteHost());
				otpLog.setType("MAJ_BKL");
				otpLog.setPhone(request.getTelephone());
				

				logRepository.save(otpLog);
				
				//
				return response.getBody();
			}
			else {
				throw new Exception("erreur de appel"); 
			}
			
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}




}
