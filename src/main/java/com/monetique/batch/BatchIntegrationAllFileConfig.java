package com.monetique.batch;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.monetique.batch.item.LineProcessor;
import com.monetique.batch.item.LineReaderMultiFiles;
import com.monetique.batch.item.LinesWriter;
import com.monetique.entities.Clearing;
import com.monetique.model.helper.ClearingHelper;
import com.monetique.model.helper.CustomMultiResourcePartitioner;

@Configuration
@EnableBatchProcessing
public class BatchIntegrationAllFileConfig {

    @Autowired 
    private JobBuilderFactory jobs;
    
    @Autowired 
    private StepBuilderFactory steps;



/*    
   @Bean(name = "allFilesJob")
    public Job partitionerJob() throws UnexpectedInputException, ParseException, IOException {
        return jobs.get("allFilesJob")
          .start(partitionStep())
          .build();
    }*/
    
    @Bean(name="partitionStep")
    public Step partitionStep() throws UnexpectedInputException, ParseException, IOException {
        return steps.get("partitionStep")
          .partitioner("slaveStep", partitioner())
          .step(slaveStep())
          .taskExecutor(taskExecutor())
          .build();
    }
    
    @Bean
    public CustomMultiResourcePartitioner partitioner() throws IOException {
        
        List<Path> resources=  ClearingHelper.getAllFilesNames();
        CustomMultiResourcePartitioner partitioner = new CustomMultiResourcePartitioner(resources);
        partitioner.setResources(resources);
        return partitioner;
    }


    @Bean
    protected Step slaveStep() {
        return steps.get("slaveStep").<String, Clearing> chunk(50)
          .reader(itemReaderMulti(null))
          .processor(itemProcessorMulti())
          .writer(itemWriterMulti())
          .listener(itemReaderMulti(null))
          .build();
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


 
    @Bean
    @StepScope
    public LineReaderMultiFiles itemReaderMulti(@Value("#{stepExecutionContext[fileName]}") String filename) {
    	LineReaderMultiFiles itemReader=new LineReaderMultiFiles(filename);
    	return itemReader;
    }
    

    @Bean
    @StepScope
    public ItemProcessor<String, Clearing> itemProcessorMulti() {
        return new LineProcessor();
    }	

    @Bean
    public ItemWriter<Clearing> itemWriterMulti() {
        return new LinesWriter();
    }



}
