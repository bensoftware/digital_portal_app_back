package com.monetique.batch.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import com.monetique.model.helper.ClearingHelper;
import com.monetique.repositories.ClearingFileRepository;

public class LineReader implements ItemReader<String>, StepExecutionListener {
	

	@Autowired
	ClearingFileRepository clearingFileRepository;
	
	private final Logger logger = LoggerFactory.getLogger(LineReader.class);
    
    String file;
    Stream<String> lines;
    Optional<ClearingFile>  clearingFile=null;

    
  		
  	        
  	   
    
    Iterator<String> list=null;
    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Reader initialized.");
  
        this.file="BCLEAR.020318.9425.00018";
        
        this.clearingFile=clearingFileRepository.findById(file);
        
        try {
        	list=   ClearingHelper.getList(this.file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    
    }

    @Override
    public String read() throws Exception {
    	
    	if(clearingFile.isPresent()) {
    		System.out.println("file deja integré "+file);
    		return null;
    	}
    		
    	
    	
    	if(list.hasNext()) {
    		return list.next();
    	}else {
    		return null;
    	}


    	
        
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

    	clearingFileRepository.save(new ClearingFile(file));
        logger.debug("Line Reader ended.");
        return ExitStatus.COMPLETED;
    }
}
