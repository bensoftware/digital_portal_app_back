package com.monetique.batch;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
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

import com.monetique.batch.item.LineProcessor;
import com.monetique.batch.item.LineReaderGIMTELFiles;
import com.monetique.batch.item.LinesWriter;
import com.monetique.entities.Clearing;
import com.monetique.model.helper.ClearingHelper;
import com.monetique.model.helper.CustomMultiResourceGIMELPartitioner;

@Configuration
@EnableBatchProcessing
public class BatchIntegrationClearingGIMTELConfig {


    
    @Autowired 
    private StepBuilderFactory steps;

    @Autowired
    TaskExecutor taskExecutor;




/*    
   @Bean(name = "allFilesJob")
    public Job partitionerJob() throws UnexpectedInputException, ParseException, IOException {
        return jobs.get("allFilesJob")
          .start(partitionStep())
          .build();
    }*/
    
    @Bean(name="integrationGIMTELStep")
    public Step partitionStep() throws UnexpectedInputException, ParseException, IOException {
    	return steps.get("integrationGIMTELStep")
          .partitioner("GIMELStep", partitioner())
          .step(slaveStep())
          .taskExecutor(taskExecutor)
          .build();
    }
    
    @Bean
    public CustomMultiResourceGIMELPartitioner partitioner() throws IOException {
        
        List<Path> resources=  ClearingHelper.getAllFilesNamesGIMTEL();
        CustomMultiResourceGIMELPartitioner partitioner = new CustomMultiResourceGIMELPartitioner(resources);
        partitioner.setResources(resources);
        return partitioner;
    }


    @Bean(name="GIMELStep")
    protected Step slaveStep() {
        return steps.get("GIMELStep").<String, Clearing> chunk(50)
          .reader(itemReaderMulti(null))
          .processor(itemProcessorMulti())
          .writer(itemWriterMulti())
          .build();
    }
    
      @Bean(name="itemReaderGIMTEL")
    @StepScope
    public LineReaderGIMTELFiles itemReaderMulti(@Value("#{stepExecutionContext[fileName]}") String filename) {
    	LineReaderGIMTELFiles itemReader=new LineReaderGIMTELFiles(filename);
    	return itemReader;
    }
    

    @Bean(name="itemProcessorGIMTEL")
    public ItemProcessor<String, Clearing> itemProcessorMulti() {
        return new LineProcessor();
    }	

    @Bean(name="itemWriterGIMTEL")
    public ItemWriter<Clearing> itemWriterMulti() {
        return new LinesWriter();
    }



}
