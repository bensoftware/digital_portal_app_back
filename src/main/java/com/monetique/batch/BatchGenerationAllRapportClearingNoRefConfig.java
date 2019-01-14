package com.monetique.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.monetique.batch.item.LineReaderAllRapportNoRef;
import com.monetique.batch.item.LinesWriterAllRapportNoRef;
import com.monetique.entities.ClearingRejeter;

@Configuration
@EnableBatchProcessing
public class BatchGenerationAllRapportClearingNoRefConfig {

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
    protected Step StepAllRapportNoRef() {
        return steps.get("StepAllRapportNoRef").<ClearingRejeter, ClearingRejeter> chunk(1000)
          .reader(ItemReaderAllRapportNoRef())
          .writer(itemWriterAllRapportNoRef())
          .build();
    }
    
  

 
    @Bean
    public ItemReader<ClearingRejeter> ItemReaderAllRapportNoRef() {
    	return new LineReaderAllRapportNoRef();
    }

  

    @Bean
    public ItemWriter<ClearingRejeter> itemWriterAllRapportNoRef() {
        return new LinesWriterAllRapportNoRef();
    }



}
