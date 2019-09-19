package com.monetique;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;



/**
 * @author bpm0318
 *
 */
@Configuration
@EnableScheduling
public class TachePlannifierConfig {

	
/*	@Scheduled(cron = "${cron.expressionEpuis}")
	public void checkEpuisement()  {
		try {
		//	System.out.println("epuisement");
			notificationService.checkEpuisement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron = "${cron.expressionExp}")
	public void checkExpiration()  {
		try {
		//	System.out.println("expiration");
			notificationService.checkExpiration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
