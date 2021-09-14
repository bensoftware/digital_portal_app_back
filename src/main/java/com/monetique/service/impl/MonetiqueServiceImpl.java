package com.monetique.service.impl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.dto.monetique.ResponseDto;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.MonetiqueService;

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
		ResponseDto  responseDto=new ResponseDto();;
		
		try {
			 String url= urlMonetiqueService+"/getClientDataBy/"+cif;
			 ResponseEntity<ResponseDto> response=restTemplate.getForEntity(url,ResponseDto.class);
			 
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
		
		try {
			 String url= urlMonetiqueService+"/createCard";
			 
			 if(clientCifDto !=null);
			 ResponseEntity<ResponseDto> response=restTemplate.postForEntity(url,clientCifDto,ResponseDto.class);
			///asuivvre
			 if(response.getStatusCode().equals(HttpStatus.OK)) {
				 if(response.getBody()==null) {
					 responseDto.setErrorCode(1);
					 responseDto.setErrorMessage("Le clientCifDto est null  suite à l'appel à monetique service");
					
				 }
				 responseDto= response.getBody();
		
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

}
