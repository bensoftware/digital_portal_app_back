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
import com.monetique.service.MonetiqueService;

@Service
public class MonetiqueServiceImpl implements MonetiqueService{

	@Autowired
	RestTemplate restTemplate;

	@Value("${url.monetique.service}")
	private String urlMonetiqueService;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public ClientCifDto getClientDataByCif(String cif) throws Exception {
		// TODO Auto-generated method stub
		ClientCifDto  clientCifDto=null;
		try {
			 String url= urlMonetiqueService+"/getClientDataBy/"+cif;
			 ResponseEntity<ResponseDto> response=restTemplate.getForEntity(url,ResponseDto.class);
			 
			 if(response.getStatusCode().equals(HttpStatus.OK)) {
				 if(response.getBody()==null) {
					 throw new Exception("Le clientCifDto est null  suite à l'appel à monetoique service");
				 }
				 ResponseDto res= response.getBody();
				 if(res.getErrorCode()!=0) {
					 throw new Exception(res.getErrorMessage());
				 }
				 clientCifDto= res.getData();
			 }else {
				 throw new Exception("Erreur lors de l'appel de l'API MONETIQUE SERVICE");
			 }
		} catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return clientCifDto;
	}
	
	

	@Override
	public boolean createCarte(ClientCifDto clientCifDto) throws Exception {
		// TODO Auto-generated method stub
		// ClientCifDto clientCifDtoRturn;
	    boolean verif= false;
		try {
			 String url= urlMonetiqueService+"/createCard";
			 
			 if(clientCifDto !=null);
			 ResponseEntity<ResponseDto> response=restTemplate.postForEntity(url,clientCifDto,ResponseDto.class);
			///asuivvre
			 if(response.getStatusCode().equals(HttpStatus.OK)) {
				 if(response.getBody()==null) {
					 throw new Exception("Le clientCifDto est null  suite à l'appel à monetique service");
				 }
				 ResponseDto res= response.getBody();
				 if(res.getErrorCode()!=0) {
					 throw new Exception(res.getErrorMessage());
				 }
				 verif=true;
				 clientCifDto=(ClientCifDto) res.getData();
			 }else {
				 throw new Exception("Erreur lors de l'appel de l'API MONETIQUE SERVICE");
			 }
		} catch (Exception e) {
			// TODO: handle exception
			 throw new Exception(e.getMessage());
		}
		return verif;
	}

}
