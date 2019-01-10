package com.monetique.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.monetique.batch.item.LineProcessor;
import com.monetique.batch.item.LineReader;
import com.monetique.batch.item.LinesWriter;
import com.monetique.entities.Clearing;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

      @Autowired 
    private StepBuilderFactory steps;


    
/*    @Bean(name="fileJob")
    public Job fileJob() throws UnexpectedInputException, ParseException, MalformedURLException {
    	return jobs.get("fileJob")
    	          .start(slaveStepFile())
    	          .build();
    }
*/
    @Bean
    protected Step slaveStepFile() {
        return steps.get("slaveStepFile").<String, Clearing> chunk(50)
          .reader(itemReader())
          .processor(itemProcessor())
          .writer(itemWriter())
          .build();
    }
    
  

 
    @Bean
    public ItemReader<String> itemReader() {
    	return new LineReader();
    }

    @Bean
    public ItemProcessor<String, Clearing> itemProcessor() {
        return new LineProcessor();
    }	

    @Bean
    public ItemWriter<Clearing> itemWriter() {
        return new LinesWriter();
    }



}
