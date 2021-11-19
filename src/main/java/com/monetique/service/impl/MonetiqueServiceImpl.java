package com.monetique.service.impl;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.dto.monetique.ResponseDto;
import com.monetique.dto.monetique.FileDto;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.MonetiqueService;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.repositories.OtpLogRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class MonetiqueServiceImpl implements MonetiqueService{

	@Autowired
	RestTemplate restTemplate;

	@Value("${url.monetique.service}")
	private String urlMonetiqueService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	OtpLogRepository otpLogRepository;
	
	
//	@Override
//	public ClientCifDto getClientDataByCif(String cif) throws Exception {
//		// TODO Auto-generated method stub
//		ClientCifDto  clientCifDto=null;
//		try {
//			 String url= urlMonetiqueService+"/getClientDataBy/"+cif;
//			 ResponseEntity<ResponseDto> response=restTemplate.getForEntity(url,ResponseDto.class);
//			 
//			 if(response.getStatusCode().equals(HttpStatus.OK)) {
//				 if(response.getBody()==null) {
//					 throw new Exception("Le clientCifDto est null  suite à l'appel à monoique service");
//				 }
//				 ResponseDto res= response.getBody();
//				 if(res.getErrorCode()!=0) {
//					 throw new Exception(res.getErrorMessage());
//				 }
//				 clientCifDto= res.getData();
//			 }else {
//				 throw new Exception("Erreur lors de l'appel de l'API MONETIQUE SERVICE");
//			 }
//		} catch (Exception e) {
//			// TODO: handle exception
//			 throw new Exception(e.getMessage());
//		}
//		return clientCifDto;
//	}
//	
	@Override
	public ResponseDto getClientDataByCif(String cif) throws Exception {
		// TODO Auto-generated method stub	
		ResponseDto  responseDto=new ResponseDto();
		HttpHeaders headers= new HttpHeaders();
		headers.set("authorization", "BpmMonetique");
		HttpEntity<String> entete = new HttpEntity<String>("parameters", headers);
		try {
			 String url= urlMonetiqueService+"/getClientDataBy/"+cif;
			 
	            ResponseEntity<ResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, entete, ResponseDto.class);
			 
			 if(response.getStatusCode().equals(HttpStatus.OK)) {
				 if(response.getBody()==null) {
					 responseDto.setErrorCode(1);
					 responseDto.setErrorMessage("Le reponse de l'API est vide suite à l'appel à monetique service pour le cif : "+cif);
					 //throw new Exception("Le clientCifDto est null  suite à l'appel à monoique service");
				 }
				 responseDto= response.getBody();
				 
			 }else {
				 responseDto.setErrorCode(1);
				 responseDto.setErrorMessage("Erreur lors de l'appel de l'API MONETIQUE SERVICE pour le cif : "+cif);
				 
			 }
		} catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return responseDto;
	}
	
	


//	@Override
//	public boolean createCarte(ClientCifDto clientCifDto) throws Exception {
//		// TODO Auto-generated method stub
//		// ClientCifDto clientCifDtoRturn;
//		if(clientCifDto ==null)
//			throw new Exception("clientCifDto null");
//		
//	    boolean verif= false;
//		try {
//			 String url= urlMonetiqueService+"/createCard";
//			 
//			 if(clientCifDto !=null);
//			 ResponseEntity<ResponseDto> response=restTemplate.postForEntity(url,clientCifDto,ResponseDto.class);
//			///asuivvre
//			 if(response.getStatusCode().equals(HttpStatus.OK)) {
//				 if(response.getBody()==null) {
//					 throw new Exception("Le clientCifDto est null  suite à l'appel à monetique service");
//				 }
//				 ResponseDto res= response.getBody();
//				 if(res.getErrorCode()!=0) {
//					 throw new Exception(res.getErrorMessage());
//				 }
//				 verif=true;
//				 clientCifDto=(ClientCifDto) res.getData();
//			 }else {
//				 throw new Exception("Erreur lors de l'appel de l'API MONETIQUE SERVICE");
//			 }
//		} catch (Exception e) {
//			// TODO: handle exception
//			 throw new Exception(e.getMessage());
//		}
//		return verif;
//	}

	@Override
	public ResponseDto createCarte(ClientCifDto clientCifDto) throws Exception {
		// TODO Auto-generated method stub
		// ClientCifDto clientCifDtoRturn;
		ResponseDto  responseDto=new ResponseDto();
		
		if(clientCifDto ==null) {
			 responseDto.setErrorCode(1);
			 responseDto.setErrorMessage("clientCifDto null");
			
		}
		// get ID USER
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		

		

		
		String username=claims.getSubject();
		clientCifDto.setPUser(username);
		clientCifDto.setApikey("BpmMonetique");
	
		HttpHeaders headers= new HttpHeaders();
		headers.set("authorization", "BpmMonetique");

		HttpEntity<ClientCifDto> requete = new HttpEntity<>(clientCifDto,headers);
				
		
	
		try {
			 String url= urlMonetiqueService+"/createCard";
			 
			 if(clientCifDto !=null);
				ResponseEntity<ResponseDto> response=restTemplate.postForEntity(url, requete, ResponseDto.class);
			
			 if(response.getStatusCode().equals(HttpStatus.OK)) {
				 if(response.getBody()==null) {
					 responseDto.setErrorCode(1);
					 responseDto.setErrorMessage("Le clientCifDto est null  suite à l'appel à monetique service");
					
				 }
				 responseDto= response.getBody();
				 
				 // log
			   		OtpLog otpLog=new OtpLog();
			   		otpLog.setDate(new Date());
					otpLog.setUserName(username);
					otpLog.setHost(request.getRemoteHost());
					otpLog.setType("CREATION CARTE DEBIT");

					otpLogRepository.save(otpLog);
		
			 }else {
				 responseDto.setErrorCode(1);
				 responseDto.setErrorMessage("Erreur lors de l'appel de l'API MONETIQUE SERVICE");
				
			 }
		} catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return responseDto;
	}
	
	
	@Override
	public FileDto sendFile(MultipartHttpServletRequest multipartHttpServletRequest,String cif) throws Exception {
		
		FileDto fileDto=new FileDto();	
		Iterator<String> itr = multipartHttpServletRequest.getFileNames();
		String filename=itr.next();
		MultipartFile mFile = multipartHttpServletRequest.getFile(filename);
		fileDto.setMultipartHttpServletRequest(mFile.getBytes());
		fileDto.setCif(cif);
		
		HttpHeaders headers= new HttpHeaders();
		headers.set("authorization", "BpmMonetique");
		HttpEntity<FileDto> requete = new HttpEntity<>(fileDto,headers);

		try {
			String url= urlMonetiqueService+"/getFile";
			 if(fileDto !=null);
				ResponseEntity<FileDto> response=restTemplate.postForEntity(url,requete,FileDto.class);
				if(response.getStatusCode().equals(HttpStatus.OK)) {
				 fileDto= response.getBody();
				 return fileDto;
			 }else {
				 fileDto.setErrorMessage("Erreur lors de l'appel de l'API MONETIQUE SERVICE");
			 }
		} catch (Exception e) {
			 throw new Exception("Erreur lors de l'appel  de monetique Service : "+e.getMessage());
		}
		return fileDto;
	}
	
	
}
