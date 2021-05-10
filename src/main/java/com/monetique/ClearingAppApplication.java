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
import com.monetique.service.MajService;
import com.monetique.service.MerchantService;
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
	MajService majService;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
	/*	Merchant m=new Merchant("PT191126.1459.016219", "PT191126.1459.016219", "GCD", "", "38491505", "HMER");
		
		RequestMerchant r=new RequestMerchant();
		r.setDebut(new Date(1618750800000L));
		r.setFin(new Date(1619010000000L));
		r.setMerchant(m);
		
		merchantService.generationReleveMerchant(r);
		System.out.println("OK");*/
	//	ResponseMerchant dto=merchantService.getAllMerchant();
	//	System.out.println(dto);
	//	Jour jour=new Jour(2, 5, 2019);
	//	clearingBatchService.getConsultationCompense(jour);
		
		//clearingBatchService.removeFile("BCLEAR.180919.75279.00018");
		//clearingBatchService.removeFile("BCLEAR.020519.72174.00018");

		
/*		Operateur operateur=operateurRepository.findById(1).get();
		
		TypeMontant t=null;
		
		t=new TypeMontant(10, operateur);
		montantRepository.save(t);
		t=new TypeMontant(20, operateur);
		montantRepository.save(t);

		t=new TypeMontant(30, operateur);
		montantRepository.save(t);

		t=new TypeMontant(50, operateur);
		montantRepository.save(t);

		t=new TypeMontant(100, operateur);
		montantRepository.save(t);

		t=new TypeMontant(200, operateur);
		montantRepository.save(t);
		t=new TypeMontant(300, operateur);
		montantRepository.save(t);
		t=new TypeMontant(500, operateur);
		montantRepository.save(t);

		t=new TypeMontant(1000, operateur);
		montantRepository.save(t);*/

		//	sessionApiService.saveSessionApi("mbanking", "mbanking123@");
		
		
		
	//	ruleService.addActionsToRule(actionService.getListeAction(), 1);
		
//		User user=new User( "Admin", "12345", "Admin", "Admin", "s.moulaye@bpm.mr", new Date(),true);
		
  //  	iuserService.addNewUser(user);
		

		
	//	iuserService.addRuleToUser(ruleService.getRule(1L), 1);
		
//		List<Action> acs=actionService.getListeAction();
		

  //ruleService.addActionToRule(actionService.findAction(14L), 1);
		
		/*List<Operateur> list =operateurService.getAllOperator();
		
		Operateur p = operateurService.getOperatorByCode(1);
		Operateur p2 = operateurService.getOperatorByCode(3);
		
		System.out.println("fin");*/
//	List<CarteStock> list=	integrationService.integrationVoucher(2, "voucher1.txt");
		
//	System.out.println(list.size());

	/*	Path path1 = Paths.get("C:\\CarteApp\\Chinguitel\\archive\\voucher1.txt");
		
		Stream<String> line = Files.lines(path1,StandardCharsets.ISO_8859_1);
		
		Path path = Paths.get("C:\\CarteApp\\Chinguitel\\archive\\voucher2.txt");
		

		List<String> list= line.filter(e -> e.length()>0).map(e->securiteService.setCryptageLigne(e)).collect(Collectors.toList());
		
		Files.write(path, list);
		
		line.close();*/
		
	//	notificationService.checkEpuisement();
		///notificationService.checkExpiration();
		
		
	
		
	}
	
	

}

