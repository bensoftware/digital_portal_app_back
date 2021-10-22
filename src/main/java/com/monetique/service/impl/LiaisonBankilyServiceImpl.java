package com.monetique.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
import com.monetique.dto.ResponseDto;
import com.monetique.dto.VerificationMobileRequest;
import com.monetique.dto.VerificationMobileResponse;
import com.monetique.helper.CorrespondanteCodeHelper;
import com.monetique.helper.DateHelper;
import com.monetique.repositories.AlertRepository;
import com.monetique.repositories.ExceptionMessageRepository;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.ILiaisonBankilyService;
import com.monetique.service.NotificationService;
import com.monetique.service.TransferService;
import com.monetique.um.dao.entities.Alert;
import com.monetique.um.dao.entities.ExceptionMessage;
import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dao.entities.OtpLog;
import com.monetique.um.dao.entities.Params;
import com.monetique.um.dao.entities.Superviseur;
import com.monetique.um.dao.entities.User;
import com.monetique.um.dao.repositories.LiaisonBankilyRepository;
import com.monetique.um.dao.repositories.OtpLogRepository;
import com.monetique.um.dao.repositories.ParamsRepository;
import com.monetique.um.dao.repositories.SuperviseurRepository;
import com.monetique.um.dao.repositories.UserRepository;
import com.monetique.um.dto.VerificationImalResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
@Transactional
public class LiaisonBankilyServiceImpl implements ILiaisonBankilyService{

	@Value("${host.liaison.n1}")
	String urlLiaison;

    @Value("${host.liaison.n2}")
	String urlLiaison2;
    
    @Value("${host.bankilydb}")
	String urlVerifMobile;
	@Value("${host.urlDocPdf}")
	String urlDocPdf;
	@Value("${host.urlLiaisonQuotidien}")
	String urlLiaisonQuotidien;
	@Value("${host.urlDocLiaison}")
	String urlDocLiaison;
	@Value("${environnement}")
	String environnement;
	@Value("${partage.username}")
	String usernamePartage;
	
	@Value("${partage.password}")
	String pwdPartage;
	
	@Value("${partage.ip}")
	String ipPartage;
	
	@Value("${url.partage}")
	String urlPartageQuot;
	
	@Autowired
    TransferService transferService;
	
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
	@Autowired
	ParamsRepository paramsRepository;
	
    private ServletContext servletContext;
    
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
		//String url= "http://30.30.1.140:8854/getVerificationImalByCif/"+cif;	
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
		//String url= urlVerifMobile+"/getUserIdByTelephone/"+phone;

		String url="http://30.30.1.148:7834/getUserIdByTelephone/"+phone;
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

	@Override
	public void uploadFile(HttpServletRequest request,long id) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		MultipartHttpServletRequest mRequest;
	    mRequest = (MultipartHttpServletRequest) request;
	    LiaisonBankily bankily=liaisonBankilyRepository.findById(id).get();
		  String directory =urlDocPdf;		   
		    String lien="";
		   
		    String filepath = Paths.get(directory,lien).toString();
		    System.out.println("nom : "+filepath);
	   
		    Iterator<String> itr = mRequest.getFileNames();
		    
		    if (itr.hasNext()) {
		    
		    	MultipartFile mFile = mRequest.getFile(itr.next());
	        
		    	String fileName = "enregistrement_"+bankily.getCif()+"_"+bankily.getTelephone()+"_"+bankily.getNni()+"_"+formatter.format(new Date())+".pdf";
		    	File file = new File(directory+fileName);
		    	updateDocLiaison(fileName, id);
			    try {
					FileCopyUtils.copy(mFile.getBytes(),file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    	    }
		
	}

	@Override
	public void updateDocLiaison(String doc, long id) {
		liaisonBankilyRepository.updateDocLiaison(doc, id);		
	}

	@Override
	public void generationPdf(HttpServletResponse resonse, String fileName) throws IOException {
		String directory =urlDocPdf;
		System.out.println(directory + fileName);
		File file = new File(Paths.get(directory + fileName).toString());
        //String filepath = Paths.get(directory,lien).toString();
      //  resonse.setContentType(mediaType.getType());
        
        resonse.setContentType("application/x-download");
        resonse.setHeader("Content-Disposition", String.format("attachment; filename=\""+fileName+"\""));
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
	}

	@Override
	public ResponseDto uploadFileAutomatique(HttpServletRequest request, String cif, String nni, String telephone)
			throws Exception {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		MultipartHttpServletRequest mRequest;
	    mRequest =(MultipartHttpServletRequest) request;
		    String directory =urlDocPdf;		   
		    String lien="";
		    String filepath = Paths.get(directory,lien).toString();
		    System.out.println("nom : "+filepath);
		    Iterator<String> itr = mRequest.getFileNames();
		    if (itr.hasNext()) {
		    	MultipartFile mFile = mRequest.getFile(itr.next());
		    	String fileName = "enregistrement_"+cif+"_"+telephone+"_"+nni+"_"+formatter.format(new Date())+".pdf";
		    	File file = new File(directory+fileName);
			    try {
					FileCopyUtils.copy(mFile.getBytes(),file);
					ResponseDto res=new ResponseDto();
					res.setFilename(fileName);
					return res;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new Exception("Erreur upload "+fileName);
				}
	    	    }
		    return null;
	}

	@Override
	public Set<String> generateAllLiaisonLocal() throws Exception{
		 
		Set<String> res= new HashSet<>();
		 
			// recuperation date index 
			Params params=paramsRepository.findById(1L).get();
			Date dateIntex= params.getDate();
			dateIntex=DateHelper.getDateSansHeure(dateIntex);
			Date dateDu= DateHelper.getDateDu(dateIntex);
			
			// recuperation date actuel
			Date courant = DateHelper.getDateSansHeure(new Date());
			Date dateAu= DateHelper.getDateAu(courant);

			String dossierJour=null;
			String dossierClient=null;
			String fichierPj=null;
			String fichierId=null;
			
			String prefix = params.getPrefix();
			
			//SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
			
			List<LiaisonBankily> liaisonBankilies = new ArrayList<LiaisonBankily>();
			String urlDossierClient;
			
			//System.err.println(" du "+dateDu);
		//	System.err.println("AU "+dateAu);
			
			// liste des liaison approuvé entre 2 dates
			List<LiaisonBankily> bankilies=getAllLiaisonBankilyApprove(dateDu,dateAu);
			//System.out.println(bankilies);
			for(LiaisonBankily liaison : bankilies) {
			// information necessaire pour jasper	
	        liaisonBankilies.add(new LiaisonBankily(liaison.getIdGroupe(), liaison.getIdUserLiaison(), liaison.getDateLiaison(), liaison.getNni(), liaison.getTelephone(), liaison.getCif(), liaison.getCompte(), liaison.getIdUserApprobation(),
	        		liaison.getNomClient(), liaison.getPrenomClient(), liaison.getNomFamille(), liaison.getPrenomPere(), liaison.getDateApprobation(), liaison.getImageUrl(), liaison.getDocument()));

	          
	        dossierJour=DateHelper.getDateDir(liaison.getDateApprobation());
	        
	        res.add(dossierJour);
	        
	        dossierClient=liaison.getCif()+"_"+liaison.getTelephone()+"_"+liaison.getNni();
	        // nom dossier
	        urlDossierClient=urlLiaisonQuotidien+dossierJour+prefix+dossierClient;
			   // nom du fichier
	        fichierId=dossierClient+".pdf";
			
			Path path = Paths.get(urlDossierClient);
	        if (!Files.exists(path)) {
	        	// creation du dossier
	            Files.createDirectories(path);
	          //  System.out.println("Directory created");
	            Map<String, Object> map=new HashMap<>();
	     		File fileD=ResourceUtils.getFile(urlDocLiaison);
	     		JasperReport jasperReport=JasperCompileManager.compileReport(fileD.getAbsolutePath());
	     		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(liaisonBankilies);
	     		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map, dataSource);
	     		// generation fichier jasper
	            JasperExportManager.exportReportToPdfFile(jasperPrint, urlDossierClient+prefix+fichierId);
	        } 
	        fichierPj=liaison.getDocument();
	        if(fichierPj!=null) {
	        	   Path file = Paths.get(urlDocPdf+fichierPj);
	        	   
	        	   if(file!=null) {
	                   Path pathOut = Paths.get(urlDossierClient+prefix+fichierPj);

	                    if(Files.isRegularFile(file)) {
	                          // deplacer vers dossier client
	                          Files.copy(file, pathOut, StandardCopyOption.REPLACE_EXISTING);
	                     } 
	        	   }
	               
	               
	        	
	        }
	     

	        liaisonBankilies.clear();
			}
		    // maj index
			params.setDate(dateAu);
			paramsRepository.save(params);
			
		 
		 return res;
	}
	
	@Override
	public void generateAllLiaisonQuotidient() throws Exception {
		
		Params params=paramsRepository.findById(1L).get();
		String prefix=params.getPrefix();
		
	    Set<String> res= generateAllLiaisonLocal();
	
		 if(res!=null && res.size()>0) {
			
			for (String x : res) {
				sendToPartage(prefix, x);
			}
			
		  }
		
	}

	@Override
	public List<LiaisonBankily> getAllLiaisonBankilyApprove(Date debut,Date fin) {
		return liaisonBankilyRepository.getAllLiaisonBankilyApprove(debut, fin);
	}

	@Override
	public Params updateParams(Params params) throws Exception {
		params.setId(1L);
		params.setDate(new Date());
		return paramsRepository.save(params);
	}
	
	@Override
	public void sendToPartage(String prefix,String filename) throws Exception {
		
		Path path = Paths.get(urlLiaisonQuotidien+filename);
		if(Files.isDirectory(path)) {
			// creation du dossier vers partage
			transferService.createDirToPartage(urlPartageQuot+filename);
			
			try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){

			    for(Path file : listing){
                    // type fichier
			    	String fileD=file.getFileName().toString();	
		    		String fileDir=filename+prefix+fileD;
		    		
			    	if(Files.isRegularFile(file)) {
			    		// envoi vers partage
			    		transferService.transferToPartage(urlLiaisonQuotidien+fileDir, urlPartageQuot+fileDir);
			    	}
			    	// type dossier
			    	else if(Files.isDirectory(file)) {

			    		// appel recurssive
			    		sendToPartage(prefix, fileDir);
			    	}
			    }
					
			  } catch (IOException e) {
			    e.printStackTrace();
			  }	
		
			
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
