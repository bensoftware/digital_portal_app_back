package com.monetique.batch.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class LineReaderMultiFiles implements ItemReader<String>, StepExecutionListener {
	

	public LineReaderMultiFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LineReaderMultiFiles(String file) {
		super();
		System.out.println("nom "+file);
		this.filename=file;
	}

	@Autowired
	ClearingFileRepository clearingFileRepository;
	
	private final Logger logger = LoggerFactory.getLogger(LineReaderMultiFiles.class);
    
    int i=0;
    int size=0;
    Optional<ClearingFile>  clearingFile;
    String filename;
  		
  	        
  	   
    
    List<String> list= new ArrayList<>();
    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Reader initialized.");
        System.out.println("hhhhhhhhhhhhhhhhhh");
        this.clearingFile=clearingFileRepository.findById(filename);

        try {
        	list=   ClearingHelper.getList(filename);
        	size=list.size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    
    }

    @Override
    public String read() throws Exception {
    	
    	
    	if(clearingFile.isPresent()) {
    		System.out.println("file deja integré "+filename);
    		return null;
    	}
    		
    	
    	String line;
    	
    	if(size>i) {
    		line=list.get(i);
    		i++;
    		return line;
    	}else {
    		return null;
    	}

    	
        
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

    	clearingFileRepository.save(new ClearingFile(filename));
        logger.debug("Line Reader ended.");
     /*   try {
			ClearingHelper.moveFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        return ExitStatus.COMPLETED;
    }
}
