package com.monetique.batch.item;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

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

public class LineReaderGIMTELFiles implements ItemReader<String>, StepExecutionListener {
	
	@Autowired
	ClearingFileRepository clearingFileRepository;
	
    Optional<ClearingFile>  clearingFile;
    String filename;
    boolean isExist=false;
    
    Iterator<String> list= null;

	public LineReaderGIMTELFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LineReaderGIMTELFiles(String file) {
		super();
		System.out.println("Integration du clearing "+file);
		this.filename=file;
	}


	
	private final Logger logger = LoggerFactory.getLogger(LineReaderGIMTELFiles.class);


  		
  	        
  	   
    
 
    
    @Override
    public void beforeStep(StepExecution stepExecution) {
    	
        logger.debug("Line Reader initialized.");
        this.clearingFile=clearingFileRepository.findById(filename);

     	if(clearingFile.isPresent()) {
    		System.out.println("file deja integré "+filename);
    		this.isExist=true;
    	}
        
        try {
        	list=   ClearingHelper.getListTransactionGiMTELFile(filename);
		} catch (IOException e) {
			try {
				ClearingHelper.moveFileGiMTELException(filename);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
    
    }

    @Override
    public String read() throws Exception {
    	
    	
     if(isExist)
    	 return null;
    		
    	
     	if(list.hasNext()) {
    		return list.next();
    	}else {
    		return null;
    	}
    	
        
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

    	if(!isExist)
    	clearingFileRepository.save(new ClearingFile(filename));
        logger.debug("Line Reader ended.");
        try {
			ClearingHelper.moveFileGiMTEL(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ExitStatus.COMPLETED;
    }
}
