package com.monetique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.monetique.model.helper.CustomJobExecutorListener;
import com.monetique.service.IntegrationBatchService;

@SpringBootApplication
public class ClearingAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
	
		SpringApplication.run(ClearingAppApplication.class, args);

       
	
	}

	@Bean
	CustomJobExecutorListener getExecListner() {
		return new CustomJobExecutorListener((ThreadPoolTaskExecutor) taskExecutor());
	}
	

	
	@Bean
	public TaskExecutor taskExecutor() {
	        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	        taskExecutor.setMaxPoolSize(1000);
	        taskExecutor.setCorePoolSize(5);
	        taskExecutor.setQueueCapacity(1000);
	        taskExecutor.afterPropertiesSet();
	        return taskExecutor;
	    }
	

	@Autowired
	IntegrationBatchService batchService;
	

	
	
	@Override
	public void run(String... args) throws Exception {

	//	batchService.integrationCleationGIMTELBatch();
		
 //   String fdu="01022019";
 //   String fau="12022019";		
	
//    DateFormat df=new SimpleDateFormat("ddMMyyyy");

//	 Date du=df.parse(fdu);
//	 Date au =df.parse(fau);	
	 
//	 traitementClearingBatchService.verificationClGIMTELDate(du, au);
	}

}

