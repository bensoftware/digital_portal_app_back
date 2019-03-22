package com.monetique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monetique.entities.CarteStock;
import com.monetique.entities.TypeMontant;
import com.monetique.repositories.CarteStockRepository;
import com.monetique.repositories.TypeMontantRepository;
import com.monetique.service.CarteStockService;
import com.monetique.service.IntegrationService;
import com.monetique.service.NotificationService;
import com.monetique.service.SecuriteService;

@SpringBootApplication
public class ClearingAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
	
		SpringApplication.run(ClearingAppApplication.class, args);

	}


	
	@Autowired
	IntegrationService integrationService;
	
	@Autowired
	SecuriteService securiteService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	CarteStockRepository carteStockRepository;
	
	@Autowired
	TypeMontantRepository typeMontantRepository ;
	
	@Override
	public void run(String... args) throws Exception {

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

