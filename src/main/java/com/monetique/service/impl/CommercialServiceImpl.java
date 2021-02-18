package com.monetique.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.Client;
import com.monetique.dto.OtpIn;
import com.monetique.dto.OtpOut;
import com.monetique.dto.OtpResponse;
import com.monetique.service.CommercialService;
import com.monetique.service.SmsService;
@Service
public class CommercialServiceImpl implements CommercialService {
	
    
    @Value("${otpUrl}")
	String otpUrl;
	@Value("${host.bankily.digit}")
	String hostBankilyDigit;
	
	@Value("${host.bankilydb}")
	String hostBankilyDb;
	@Value("${host.bankily}")
	String hostBankily;
	
    @Autowired
	RestTemplate restTemplate;
    
    @Autowired
    SmsService smsService;
    
	@Override
	public OtpOut getOtp(OtpIn in) throws Exception {
		
        try {
            HttpHeaders headers= new HttpHeaders();
      //      headers.set("authorization", apiKey);
            

            
            HttpEntity<OtpIn> requete = new HttpEntity<>(in,headers);
            
            String url= otpUrl+"/getOtpByTelephone";
            
            
            ResponseEntity<OtpOut> response
              =restTemplate.postForEntity(url, requete, OtpOut.class);
     
            if(response.getStatusCode().equals(HttpStatus.OK)) {
                   
            	OtpOut res=response.getBody();
               
         	   return res;
                     
            }
            else {
                   throw new Exception("Message non envoyer  /////getOtpByTelephone"); 
            }
            
     } catch (Exception e) {
            throw new Exception("Message non envoyer  /////getOtpByTelephone"); 
     }
	}
	@Override
	public Client getPartyIdByTelephone(String telephone) throws Exception {

		Client res=null;
		try {
			String url= hostBankilyDigit+"/getPartyIdByTelephone/"+telephone;
			ResponseEntity<Client> response
			  = restTemplate.getForEntity(url, Client.class);
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				 res= response.getBody(); 
			}
			
		} catch (Exception e) {
           System.err.println(e);
		}
		
		return res;
	}

	@Override
	public Client getCifByPartyId(String partyId) throws Exception {

		Client res=null;
		try {
			String url= hostBankily+"/getCifByPartyId/"+partyId;
			ResponseEntity<Client> response
			  = restTemplate.getForEntity(url, Client.class);
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				 res= response.getBody(); 
			}
			
		} catch (Exception e) {
           System.err.println(e);
		}
		
		return res;
	}

	@Override
	public String getCifByTelephone(String telephone) throws Exception {
		
		String cif=null;
		
		Client digit=null;
		Client party=null;
		
		digit=getPartyIdByTelephone(telephone);
		
		if(digit!=null) {
			party=getCifByPartyId(digit.getPartyId());
			
			cif=party.getCif();
		}
		
		return cif;
		
	}
	
//	@Override
//	public Client getClientDetails(String telephone,String type) throws Exception {
//		
//		//Client res=null;
////		try {
////			String url= hostBankily+"/getCifByPartyId/"+partyId;
////			ResponseEntity<Client> response
////			  = restTemplate.getForEntity(url, Client.class);
////			if(response.getStatusCode().equals(HttpStatus.OK)) {
////				 res= response.getBody(); 
////			}
////			
////		} catch (Exception e) {
////           System.err.println(e);
////		}
//		Client client =new Client ();
//		client.setCif("605");
//		client.setFirstName("Mohamed Abdellahi");
//		client.setLastName("Bellamech");
//		client.setKyc_label("FULL_KYC");
//		client.setKycStatusId("1");
//		client.setNni("3293902236");
//		client.setPicture("pic");
//		client.setStatusLabel("ACTIVE");
//		client.setStatusId("1");
//		client.setBearer("MOBILE");
//		client.setTelephone("36445072");
//		return client;
//		
//	}
	@Override
	public Client getClientDetailsByNni(String nni) throws Exception {
		Client client=null;
	try {
		String url= hostBankilyDb+"/getInfoClientByNni/"+nni;
		ResponseEntity<Client> response
		  = restTemplate.getForEntity(url, Client.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			client= response.getBody(); 
		}
		
	} catch (Exception e) {
       System.err.println(e);
	}
		return client;
	}
	@Override
	public Client getClientDetailsByPhone(String phone) throws Exception {
		Client client=null;
	try {
		String url= hostBankilyDb+"/getInfoClientByTel/"+phone;
		ResponseEntity<Client> response
		  = restTemplate.getForEntity(url, Client.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			client= response.getBody(); 
		}
		
	} catch (Exception e) {
       System.err.println(e);
	}
		return client;
	}
	@Override
	public Client getClientDetailsByCif(String cif) throws Exception {
		Client client=null;
	try {
		String url= hostBankilyDb+"/getInfoClientByCif/"+cif;
		ResponseEntity<Client> response
		  = restTemplate.getForEntity(url, Client.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			client= response.getBody(); 
		}
		
	} catch (Exception e) {
       System.err.println(e);
	}
		return client;
	}
	@Override
	public boolean deblockByPhone(String phone) throws Exception {
		try {
			String url= hostBankilyDb+"/debloquerClientBankily/"+phone;
			ResponseEntity<String> response
			  = restTemplate.getForEntity(url, String.class);
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	@Override
	public boolean resetAttempt(String phone) throws Exception {
		try {
			String url= hostBankilyDb+"/deblocageUserTel/"+phone;
			ResponseEntity<String> response
			  = restTemplate.getForEntity(url, String.class);
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
		return false;
		
	}
	@Override
	public String resendPinTemporaire(String phone ) throws Exception {
		String pin =null;
		Client client=getClientDetailsByPhone(phone);
		
		if(client !=null) {
			String text="BIENVENUE CHEZ BANKILY, votre code PIN temporaire est de "+client.getTemporaire();
			pin=client.getTemporaire();
	        try {
	                        smsService.sendSms(text, phone);
	                        return pin;

	        } catch (Exception e) {
	                        throw new Exception("Erreur d'envoi");
	                        
	        } 
		}else return pin;
		
		
               
	}	
}
