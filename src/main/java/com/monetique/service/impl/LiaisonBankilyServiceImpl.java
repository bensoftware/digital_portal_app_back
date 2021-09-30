package com.monetique.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.AddLiaisonObject;
import com.monetique.dto.Approbation;
import com.monetique.dto.ApprobationResponse;
import com.monetique.dto.ErrorLiason;
import com.monetique.dto.Liaison;
import com.monetique.dto.LiaisonIncomplet;
import com.monetique.dto.LiaisonRequest;
import com.monetique.dto.LiaisonResponse;
import com.monetique.dto.LiaisonResponseObject;
import com.monetique.dto.ListLiaisonResponse;
import com.monetique.dto.VerificationMobileRequest;
import com.monetique.dto.VerificationMobileResponse;
import com.monetique.helper.CorrespondanteCodeHelper;
import com.monetique.repositories.AlertRepository;
import com.monetique.repositories.ExceptionMessageRepository;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.ILiaisonBankilyService;
import com.monetique.service.NotificationService;
import com.monetique.um.dao.entities.Alert;
import com.monetique.um.dao.entities.ExceptionMessage;
import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.entities.Superviseur;
import com.monetique.um.dao.entities.User;
import com.monetique.um.dao.repositories.LiaisonBankilyRepository;
import com.monetique.um.dao.repositories.OtpLogRepository;
import com.monetique.um.dao.repositories.SuperviseurRepository;
import com.monetique.um.dao.repositories.UserRepository;
import com.monetique.um.dto.VerificationImalResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Service
public class LiaisonBankilyServiceImpl implements ILiaisonBankilyService{

	@Value("${host.liaison.n1}")
	String urlLiaison;

    @Value("${host.liaison.n2}")
	String urlLiaison2;
    
    @Value("${host.bankilydb}")
	String urlVerifMobile;
    
    @Autowired
    RestTemplate restTemplate;
	@Autowired
	private LiaisonBankilyRepository liaisonBankilyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExceptionMessageRepository exceptionMessageRepository;
	@Autowired
	private SuperviseurRepository superviseurRepository;
	@Autowired
	private AlertRepository alertRepository;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	OtpLogRepository otpLogRepository;
	
	@Override
	public LiaisonBankily addLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception {
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		String username=claims.getSubject();
		liaisonBankily.setIdUserLiaison(username);
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
	   		
	   		
	   		// LOG
	   		OtpLog otpLog=new OtpLog();
	   		otpLog.setDate(new Date());
			otpLog.setUserName(username);
			otpLog.setHost(request.getRemoteHost());
			otpLog.setType("VALIDATION LIAISON BANKILY");

			otpLogRepository.save(otpLog);
			
	   		
	   		}
	   	return liaisonBankily;
	}

	@Override
	public List<LiaisonBankily> getAllLiaisonBankily() {
		return liaisonBankilyRepository.getAllLiaisonBankily();
	}

	@Override
	public ListLiaisonResponse getCompteByCif(LiaisonRequest liaisonRequest)  {
		ListLiaisonResponse res= null;
		liaisonRequest.bearerCode="WEB";
		liaisonRequest.languageId="1";
		System.out.println(liaisonRequest);
		String url= urlLiaison+"/DIGIBANKREGWEB";
		try {
			ResponseEntity<LiaisonResponseObject> response = restTemplate.postForEntity(url, liaisonRequest, LiaisonResponseObject.class) ;
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				List<LiaisonResponse> list=null;
				LiaisonResponseObject resp= response.getBody(); 
				if(resp!=null && resp.getStatus()!=null && resp.getStatus().equalsIgnoreCase("PAUSED"))
				list=  resp.getResponse().getAccountList();		
				res= new ListLiaisonResponse();
				if(resp!=null && resp.getServiceRequestId()!=null)
				res.setServiceRequestId(resp.getServiceRequestId());			
				res.setList(list);
			}
			} catch (HttpStatusCodeException ex) {
				System.err.println(ex.getResponseBodyAsString());
				System.err.println(ex.getResponseHeaders());
				System.err.println(ex.getStatusCode());
			}
	
		
		return res;
	}
	@Override
	public VerificationImalResponse getVerificationImalByCif(String cif) throws Exception {
		VerificationImalResponse res=null;
		String url= urlVerifMobile+"/getVerificationImalByCif/"+cif;
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

	@Override
	public LiaisonBankily updateLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception {
		
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		String username=claims.getSubject();

		
			LiaisonBankily bankily=liaisonBankilyRepository.findById(liaisonBankily.getId()).get();
			liaisonBankily.setDateApprobation(new Date());
			liaisonBankily.setId(bankily.getId());
			liaisonBankily.setApproved(true);
			liaisonBankily.setIdUserApprobation(username);

	   		// LOG
	   		OtpLog otpLog=new OtpLog();
	   		otpLog.setDate(new Date());
			otpLog.setUserName(username);
			otpLog.setHost(request.getRemoteHost());
			otpLog.setType("APPROBATION LIAISON BANKILY");

			otpLogRepository.save(otpLog);
			return liaisonBankilyRepository.save(liaisonBankily);

	   		
	  
	}

	@Override
	public List<LiaisonBankily> getAllLiaisonBankilyByIdGroup(long idG) {
		return liaisonBankilyRepository.getAllLiaisonBankilyByIdGroup(idG);
	}

	@Override
	public ApprobationResponse addApprobation(Approbation approbation) throws Exception {
		ApprobationResponse res= null;
		if(approbation.getUserId()==null||approbation.getModifiedBy()==null) {
			throw new Exception("Erreur survenu merci de réessayer");
		}
		approbation.setApprove("Y");
		approbation.setBearerCode("WEB");
		approbation.setLanguageId("1");
		approbation.setRemarks("OK");
		System.out.println(approbation);
		String url= urlLiaison2+"/DIGITALAPPROVAL";
		ResponseEntity<ApprobationResponse> response = restTemplate.postForEntity(url, approbation, ApprobationResponse.class) ;
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			res= response.getBody(); 			
		}		
		return res;
	}
	
	@Override
	public ApprobationResponse addRejet(Approbation approbation) throws Exception {
		ApprobationResponse res= null;
		if(approbation.getUserId()==null||approbation.getModifiedBy()==null) {
			throw new Exception("Erreur survenu merci de réessayer");
		}
		approbation.setApprove("N");
		approbation.setBearerCode("WEB");
		approbation.setLanguageId("1");
		approbation.setRemarks("OK");
		System.out.println(approbation);
		String url= urlLiaison2+"/DIGITALAPPROVAL";
		ResponseEntity<ApprobationResponse> response = restTemplate.postForEntity(url, approbation, ApprobationResponse.class) ;
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			res= response.getBody(); 			
		}		
		return res;
	}

	@Override
	public AddLiaisonObject addLiaison(Liaison liaison) throws Exception {
		liaison.setKycValue(CorrespondanteCodeHelper.getNniFormat(liaison.getKycValue()));
		AddLiaisonObject res= null;
		liaison.setKycIdType("nni");
		liaison.setBearerCode("WEB");
		liaison.setLanguageId("1");
		liaison.setIsCreateNewAccount("N");
		System.out.println(liaison);
		String url= urlLiaison+"/resume/DIGIBANKREGWEB";
		ResponseEntity<AddLiaisonObject> response = restTemplate.postForEntity(url, liaison, AddLiaisonObject.class) ;
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			res= response.getBody(); 
			List<ErrorLiason> list=  res.getErrors();
			res.setErrors(list);
		}
		System.err.println(res);
		return res;
	}

	@Override
	public long getGroupeByUsername(String username) throws Exception {
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		username=claims.getSubject();
		Optional<User> userOp=Optional.of(userRepository.findByuserName(username));	
   		long idG = 0;
	   	if(userOp!=null) {
	   		User user=userOp.get();	 
	   		Set<Groupe> listGroupe=user.getGroupes();	
	   		for(Groupe ac: listGroupe) {
	   			if(ac.isActive()==true) {
	   				idG=ac.getId();
	   				break;
	   			}
	   		}
	   		}
	   	return idG;
	}

	@Override
	public VerificationImalResponse getUserIdByLogin(String username) throws Exception {
		VerificationImalResponse res=null;
		String url= urlVerifMobile+"/getUserIdByLogin/"+username;
		ResponseEntity<VerificationImalResponse> response = restTemplate.getForEntity(url, VerificationImalResponse.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		return res;
	}

	@Override
	public VerificationImalResponse getUserIdByTelephone(String phone) throws Exception {
		VerificationImalResponse res=null;
		String url= urlVerifMobile+"/getUserIdByTelephone/"+phone;
		ResponseEntity<VerificationImalResponse> response = restTemplate.getForEntity(url, VerificationImalResponse.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		return res;
	}

	@Override
	public LiaisonBankily updateRejetLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception {
		
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
		String username=claims.getSubject();
		
		
		LiaisonBankily bankily=liaisonBankilyRepository.findById(liaisonBankily.getId()).get();
		liaisonBankily.setDateRejet(new Date());
		liaisonBankily.setIdUserRejet(username);
		liaisonBankily.setId(bankily.getId());
		liaisonBankily.setRejeted(true);
		
		// LOG
   		OtpLog otpLog=new OtpLog();
   		otpLog.setDate(new Date());
		otpLog.setUserName(username);
		otpLog.setHost(request.getRemoteHost());
		otpLog.setType("REJECT LIAISON BANKILY");

		otpLogRepository.save(otpLog);
		
		return liaisonBankilyRepository.save(liaisonBankily);
	}

	@Override
	public LiaisonBankily getLiaisonBankilyByTelephone(String telephone) {
		return liaisonBankilyRepository.getLiaisonBankilyByTelephone(telephone);
	}

	@Override
	public ExceptionMessage addExceptionMessage(ExceptionMessage exceptionMessage) throws Exception {
		exceptionMessage.setDate(new Date());
		return exceptionMessageRepository.save(exceptionMessage);
	}

	@Override
	public Alert addAlert(Alert alert) throws Exception {
		String pattern = "dd-MM-yyyy HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		alert.setIdUser(claims.getSubject());
		alert.setDate(new Date());
		LiaisonBankily bankily=liaisonBankilyRepository.getLiaisonBankilyByCif(alert.getCif());
		if(bankily==null||bankily.isApproved()==true) {
			 alertRepository.save(alert);
			 List<String> list=new ArrayList<>();
				List<Superviseur> superviseurs=superviseurRepository.getAll();
				for(Superviseur superviseur:superviseurs) {
					list.add(superviseur.getTelephone());		
				}
				try {
					notificationService.sendSms(list, "Tentative d'une liaison Bankily CIF: "+alert.getCif()+" date: "+simpleDateFormat.format(alert.getDate()));
				} catch (Exception e) {
					e.printStackTrace();
				}	
		}
		return alert;
	}

	@Override
	public VerificationMobileResponse getVerificationMobile(VerificationMobileRequest mobileRequest) throws Exception {
		VerificationMobileResponse res=null;
		String url= urlVerifMobile+"/getVerificationMobile";
		ResponseEntity<VerificationMobileResponse> response = restTemplate.postForEntity(url,mobileRequest, VerificationMobileResponse.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			 res= response.getBody(); 
		}
		return res;
	}

	@Override
	public void deleteLiaisonIncompleteMobile(LiaisonIncomplet incomplet) throws Exception {
		String url= urlVerifMobile+"/deleteLiaisonIncompleteMobile";
		ResponseEntity<Void> response=restTemplate.postForEntity(url,incomplet, Void.class);
		if(!response.getStatusCode().equals(HttpStatus.OK)) {
			response.getStatusCodeValue();
		}
	}
	
//	@Override
//	public VerificationMobileResponse deleteLiaisonIncompleteMobile(LiaisonIncomplet incomplet) throws Exception {
//		String url= urlVerifImal+"/deleteLiaisonIncompleteMobile";
//		ResponseEntity<VerificationMobileResponse> response=null;
//		try {
//			response=	restTemplate.postForEntity(url,incomplet, VerificationMobileResponse.class);
//			if(response.getStatusCode().equals(HttpStatus.OK)) {
//				//return response.get;
//				VerificationMobileResponse res= response.getBody();
//					return res;
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		return null;
//	}
}
