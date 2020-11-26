package com.monetique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.monetique.dto.Jour;
import com.monetique.security.service.AppUserData;
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
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	TraitementClearingBatchService clearingBatchService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Jour jour=new Jour(2, 5, 2019);
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

