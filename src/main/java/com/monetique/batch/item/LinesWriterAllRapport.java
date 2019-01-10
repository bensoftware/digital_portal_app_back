package com.monetique.batch.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.monetique.entities.Clearing;
import com.monetique.model.helper.ClearingHelper;
import com.monetique.repositories.ClearingRejeterRepository;
import com.monetique.repositories.ClearingRepository;



public class LinesWriterAllRapport implements ItemWriter<Clearing>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(LinesWriterAllRapport.class);
    
    int i=0;

    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Writer initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Writer ended.");
        return ExitStatus.COMPLETED;
    }

    @Override
    public void write(List<? extends Clearing> lines) throws Exception {
        for (Clearing line : lines) {
        	
        	
        	
        	logger.debug("Wrote line " + line.toString());
        }
        
        System.out.println("itt "+i);
        i++;
    }
    
    
}
