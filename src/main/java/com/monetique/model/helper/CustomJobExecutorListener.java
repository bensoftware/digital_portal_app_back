
package com.monetique.model.helper;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


public class CustomJobExecutorListener implements JobExecutionListener{


	ThreadPoolTaskExecutor executor;
	
	
	
	public CustomJobExecutorListener(ThreadPoolTaskExecutor executor) {
		super();
		this.executor = executor;
	}

	public CustomJobExecutorListener() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		//executor.shutdown();
		System.out.println("end !!!!!!");
	}

}
