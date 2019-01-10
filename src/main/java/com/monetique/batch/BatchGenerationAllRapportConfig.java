package com.monetique.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.monetique.batch.item.LineReader;
import com.monetique.batch.item.LineReaderAllRapport;
import com.monetique.batch.item.LinesWriter;
import com.monetique.batch.item.LinesWriterAllRapport;
import com.monetique.entities.Clearing;

@Configuration
@EnableBatchProcessing
public class BatchGenerationAllRapportConfig {

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
    protected Step StepAllRapport() {
        return steps.get("StepAllRapport").<Clearing, Clearing> chunk(50)
          .reader(ItemReaderAllRapport())
          .writer(itemWriterAllRapport())
          .build();
    }
    
  

 
    @Bean
    public ItemReader<Clearing> ItemReaderAllRapport() {
    	return new LineReaderAllRapport();
    }

  

    @Bean
    public ItemWriter<Clearing> itemWriterAllRapport() {
        return new LinesWriterAllRapport();
    }



}
