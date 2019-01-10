package com.monetique;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClearingAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
	
		SpringApplication.run(ClearingAppApplication.class, args);

       
	
	}
	
/*	@Autowired
     JobLauncherTestUtils jobLauncherTestUtils;
	
	
	@Autowired
	@Qualifier(value="allFilesJob")
    private JobLauncherTestUtils jobLauncherTestUtilsMulti;
	
	@Autowired
	ClearingRepository clearingRepository ; 

	@Autowired
    @Qualifier(value="fileJob")
	Job job ; 
	*/

	
	
	@Autowired
	SimpleJobLauncher jobLauncher ;


	
	@Autowired
    @Qualifier(value="partitionStep")
	Step step ; 
	
	@Autowired
    @Qualifier(value="StepAllRapport")
	Step StepAllRapport ; 
	
	
	 @Autowired 
	 private JobBuilderFactory jobs;
	
	
	@Override
	public void run(String... args) throws Exception {
	
		  try {
			  
			 /* Job job = jobs.get("allFilesJob")
			          .start(step)
			          .build();*/
			  Job job = jobs.get("allRapportJob")
			          .start(StepAllRapport)
			          .build();
			  
			  JobExecution execution = jobLauncher.run(job, new JobParameters());
			  
	    
	            System.out.println("Job Status : " + execution.getStatus());
	            System.out.println("Job completed");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Job failed");
	        }
		
	//	jobLauncherTestUtils.setJob(jobFiles);
//		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		//jobExecution.stop();
		//jobExecution.
	//	System.out.println(jobExecution.getExitStatus());
		
	}

}

