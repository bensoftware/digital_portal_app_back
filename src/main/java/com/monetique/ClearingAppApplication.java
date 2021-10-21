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

import com.monetique.security.service.AppUserData;
import com.monetique.service.EtatCivilService;
import com.monetique.service.ILiaisonBankilyService;
import com.monetique.service.MajService;
import com.monetique.service.MerchantService;
import com.monetique.service.MonetiqueService;
import com.monetique.service.TraitementClearingBatchService;
import com.monetique.um.dao.repositories.LiaisonBankilyRepository;
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
	 //       RestTemplate rest= new RestTemplate(factory);
	 //       rest.setErrorHandler(new RestTemplateResponseErrorHandler());
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
	
	@Autowired
	ILiaisonBankilyService liaisonBankilyService;
	
	@Override
	public void run(String... args) throws Exception {
	
		
	//	liaisonBankilyService.sendToPartage("/", "13-10-2021");
		
	}
	
	

}

