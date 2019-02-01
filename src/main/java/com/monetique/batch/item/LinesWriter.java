package com.monetique.batch.item;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.monetique.entities.Clearing;
import com.monetique.entities.ClearingFile;
import com.monetique.entities.ClearingRejeter;
import com.monetique.model.helper.ClearingHelper;
import com.monetique.repositories.ClearingFileRepository;
import com.monetique.repositories.ClearingRejeterRepository;
import com.monetique.repositories.ClearingRepository;



public class LinesWriter implements ItemWriter<Clearing>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(LinesWriter.class);
    
    String filename=null;
    
    ClearingFile clearingFile;
    
    
    
    public LinesWriter(String filename) {
		super();
		this.filename = filename;
	}

	public LinesWriter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
    ClearingRepository clearingRepository;
	
	@Autowired
    ClearingFileRepository clearingFileRepository;
   
    @Autowired
    ClearingRejeterRepository clearingRejeterRepository;
    
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
    	
    	Optional<ClearingFile> opt= clearingFileRepository.findById(filename);
    	
    	if(opt.isPresent()) {
    		this.clearingFile=opt.get();
    	}else {
    		this.clearingFile=clearingFileRepository.save(new ClearingFile(filename));
    	}
    	
        for (Clearing line : lines) {
        	String ref=line.getReferenceTransaction();
        	//System.out.println(line.getReferenceTransaction())
        	
        	if(line.getCodeOperation().equals("27000"))
        		System.out.println("27000");
        	
        	;
        	try {
        		
        		if(ref.matches("\\s+")) {
        			ClearingRejeter cr=ClearingHelper.getClearingRejeterByCl(line);
        			cr.setClearingFile(this.clearingFile);
        			clearingRejeterRepository.save(cr);
        		}else {
        			line.setClearingFile(clearingFile);
        		Clearing c=	clearingRepository.save(line);	
        		System.out.println(c.getPan());
        		}
        		
        		//System.out.println("accepter");
			} catch (Exception e) {
			//	System.out.println("rejeter");
				ClearingRejeter cr=ClearingHelper.getClearingRejeterByCl(line);
    			cr.setClearingFile(this.clearingFile);
				clearingRejeterRepository.save(cr);
				
			}
        	
        	logger.debug("Wrote line " + line.toString());
        }
    }
    
    
}
