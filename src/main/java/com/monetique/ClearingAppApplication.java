package com.monetique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.security.service.AppUserData;
import com.monetique.service.EtatCivilService;
import com.monetique.service.ILiaisonBankilyService;
import com.monetique.service.MajService;
import com.monetique.service.MerchantService;
import com.monetique.service.MonetiqueService;
import com.monetique.service.TraitementClearingBatchService;
import com.monetique.um.service.IActionService;
import com.monetique.um.service.IRuleService;
import com.monetique.um.service.IUserService;

@SpringBootApplication
public class ClearingAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
	
		SpringApplication.run(ClearingAppApplication.class, args);

	}

	
	


	
	@Autowired
	IUserService iuserService ;
	

	@Autowired
	EtatCivilService etatCivilService;
	
	@Autowired
	IRuleService ruleService ;
	


	
	@Autowired
    AppUserData appUserData;
	
	@Autowired
	IActionService actionService;
	


	
	@Bean
	public BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean	
	public RestTemplate getRest2() {
		    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

	        factory.setConnectTimeout(1200000);
	        factory.setReadTimeout(1200000);

	        return new RestTemplate(factory);	}

	@Autowired
	TraitementClearingBatchService clearingBatchService;
	
	@GetMapping(value="/")
	public String geLogin() {
		return "/login";
	}
	
	@Autowired
	MerchantService merchantService;
	@Autowired
	ILiaisonBankilyService bankilyService;
	
	@Autowired
	MajService majService;
	
	@Autowired
	MonetiqueService monetiqueService;
	
	
	
	@Override
	public void run(String... args) throws Exception {
	
	//	LiaisonIncomplet req=new LiaisonIncomplet();
//		req.setCif("453");
//		req.setTelephone("34212133");
//		req.setNni("1842979922");
//		req.setCompte("00018000012100004530312");
//		req.setCif("10005498");
//		req.setTelephone("22335526");
//		req.setNni("6086352883");
//		req.setCompte("00018000022100054980191");
//		bankilyService.deleteLiaisonIncompleteMobile(req);
		
	     // ClientCifDto cifDto=monetiqueService.getClientDataByCif("453");
		//System.err.println(cifDto);
		
			/*
			 * ClientCifDto clientCifDto=monetiqueService.getClientDataByCif("453");
			 * 
			 * clientCifDto.setEmail("benjudicaelle@gmail.com");
			 * clientCifDto.setTelephone("37818077");
			 * clientCifDto.setTypeCardDtos(clientCifDto.getListTypeCardDtos().get(0));
			 * clientCifDto.setAccountDtdos(clientCifDto.getListAccountDtdos().get(0));
			 * 
			 * boolean b=monetiqueService.createCarte(clientCifDto);
			 * System.err.println("OK : " + b);
			 */
		 
	}
	
	

}

