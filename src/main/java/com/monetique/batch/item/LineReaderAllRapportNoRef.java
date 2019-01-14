package com.monetique.batch.item;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.monetique.entities.ClearingFile;
import com.monetique.entities.ClearingRejeter;
import com.monetique.repositories.ClearingRejeterRepository;

public class LineReaderAllRapportNoRef implements ItemReader<ClearingRejeter>, StepExecutionListener {
	

	@Autowired
	ClearingRejeterRepository clearingRejeterRepository;
	
	private final Logger logger = LoggerFactory.getLogger(LineReaderAllRapportNoRef.class);
    
    String file;
    Stream<String> lines;
    int i=0;
    int size=0;
    Optional<ClearingFile>  clearingFile=null;

    Iterator<ClearingRejeter> iterator;

    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Reader initialized.");
        System.out.println("Generation du rapport clearing Rejeté");
        iterator=   clearingRejeterRepository.findAll().iterator();
     }

    @Override
    public ClearingRejeter read() throws Exception {
    	
    	if(iterator.hasNext()) {
    		return iterator.next();
    	}else {
    		return null;
    	}
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        logger.debug("Line Reader ended.");
        return ExitStatus.COMPLETED;
    }
}
