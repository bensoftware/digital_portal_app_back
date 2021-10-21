package com.monetique;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.monetique.service.ILiaisonBankilyService;
@Configuration
@EnableScheduling
public class LiaisonQuotidien {
	final Logger logger = LoggerFactory.getLogger(LiaisonQuotidien.class);
	@Autowired
	private ILiaisonBankilyService iLiaisonBankilyService;
	
	@Scheduled(cron = "${cron.generate.liaison}")
	public void generateAllMerchantQuotidient()  {
		try {
			logger.info("appel Batch generation releve");
			iLiaisonBankilyService.generateAllLiaisonQuotidient();
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}

}
