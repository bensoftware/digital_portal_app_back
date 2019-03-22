package com.monetique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.monetique.service.NotificationService;



@Configuration
@EnableScheduling
public class TachePlannifierConfig {

	@Autowired
	NotificationService notificationService;
	
/*	@Scheduled(cron = "${cron.expressionEpuis}")
	public void checkEpuisement()  {
		try {
			notificationService.checkEpuisement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron = "${cron.expressionExp}")
	public void checkExpiration()  {
		try {
			notificationService.checkExpiration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
