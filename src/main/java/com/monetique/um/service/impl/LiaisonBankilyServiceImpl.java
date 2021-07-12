package com.monetique.um.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.LiaisonRequest;
import com.monetique.dto.LiaisonResponse;
import com.monetique.dto.LiaisonResponseObject;
import com.monetique.dto.ListLiaisonResponse;
import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dao.entities.User;
import com.monetique.um.dao.repositories.LiaisonBankilyRepository;
import com.monetique.um.dao.repositories.UserRepository;
import com.monetique.um.dto.VerificationImalResponse;
import com.monetique.um.service.ILiaisonBankilyService;
@Service
public class LiaisonBankilyServiceImpl implements ILiaisonBankilyService{
    @Value("${host.liaison}")
	String urlLiaison;
    @Value("${host.urlVerifImal}")
	String urlVerifImal;
    @Value("${host.urlVerifMobile}")
	String urlVerifMobile;
    
    @Autowired
    RestTemplate restTemplate;
	@Autowired
	private LiaisonBankilyRepository liaisonBankilyRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public LiaisonBankily addLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception {
		System.out.println(liaisonBankily);
		Optional<User> userOp=Optional.of(userRepository.findByuserName(liaisonBankily.getIdUserLiaison()));	
	   	if(userOp!=null) {
	   		User user=userOp.get();	 
	   		Set<Groupe> listGroupe=user.getGroupes();	
	   		long idG = 0;
	   		for(Groupe ac: listGroupe) {
	   			if(ac.isActive()==true) {
	   				idG=ac.getId();
	   				break;
	   			}
	   		}
	   		if(idG>0) {
   				liaisonBankily.setIdGroupe(idG);
   				liaisonBankily.setDateLiaison(new Date());
   			    liaisonBankilyRepository.save(liaisonBankily);
	   		}
	   		}
	   	return liaisonBankily;
	}

	@Override
	public List<LiaisonBankily> getAllLiaisonBankily() {
		return liaisonBankilyRepository.findAll();
	}

	@Override
	public ListLiaisonResponse getCompteByCif(LiaisonRequest liaisonRequest) throws Exception {
		
		ListLiaisonResponse res= null;
		
		liaisonRequest.setBearerCode("WEB");
		liaisonRequest.setLanguageId("1");
		System.out.println(liaisonRequest);
		String url= urlLiaison+"/DIGIBANKREGWEB";
		ResponseEntity<LiaisonResponseObject> response = restTemplate.postForEntity(url, liaisonRequest, LiaisonResponseObject.class) ;
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			LiaisonResponseObject resp= response.getBody(); 
			
			res= new ListLiaisonResponse();
			res.setServiceRequestId(resp.getServiceRequestId());
			List<LiaisonResponse> list=  resp.getResponse().getAccountList();
			res.setList(list);
			
		}
		
		return res;
	}
	@Override
	public VerificationImalResponse getVerificationImalByCif(String cif) throws Exception {
		VerificationImalResponse res=null;
		String url= urlVerifImal+"/getVerificationImalByCif/"+cif;
		ResponseEntity<VerificationImalResponse> response = restTemplate.getForEntity(url, VerificationImalResponse.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		return res;
	}

	@Override
	public VerificationImalResponse getVerificationMobileByTelephone(String phone) throws Exception {
		VerificationImalResponse res=null;
		String url= urlVerifMobile+"/getVerificationMobileByTelephone/"+phone;
		ResponseEntity<VerificationImalResponse> response = restTemplate.getForEntity(url, VerificationImalResponse.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		return res;
	}


}
