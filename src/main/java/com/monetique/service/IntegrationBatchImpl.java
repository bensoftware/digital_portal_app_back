package com.monetique.service;


import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.monetique.model.helper.CustomJobExecutorListener;

@Service
public class IntegrationBatchImpl implements IntegrationBatchService {

	@Autowired
	SimpleJobLauncher jobLauncher ;
	
	@Autowired
	CustomJobExecutorListener customJobExecutorListener;
	
	@Autowired
    @Qualifier(value="integrationGIMTELStep")
	Step integrationGIMTELStep ; 
	
	 @Autowired 
	 private JobBuilderFactory jobs;
	
	@Override
	public void integrationCleationGIMTELBatch() throws Exception {
		
		  try {
				  Job job = jobs.get("integrationGIMTELStep")
				          .start(integrationGIMTELStep)
				          .listener(customJobExecutorListener)
				          .build();
				  
				  JobExecution execution = jobLauncher.run(job, new JobParameters());
				  
				  BatchStatus batchStatus=execution.getStatus();
		          
		        
		          
		          System.out.println("Job completed "+batchStatus.toString());
		          
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Job failed");
		            throw new Exception("NoIntegrationGIMTEL");
		        }
			
		
	}

	@Override
	public void integrationCleationSSBatch() {
		// TODO Auto-generated method stub
		
	}

}
