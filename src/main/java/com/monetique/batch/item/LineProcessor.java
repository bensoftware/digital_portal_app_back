package com.monetique.batch.item;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import com.monetique.entities.Clearing;
import com.monetique.model.helper.ClearingHelper;
import com.monetique.model.helper.LineClearing;

public class LineProcessor implements ItemProcessor<LineClearing, Clearing>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(LineProcessor.class);

    String filename;
    
    DateFormat df=null;
    
    
    
    public LineProcessor(String filename) {
		super();
		this.filename = filename;
	}

	public LineProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Processor initialized.");
        df=new SimpleDateFormat("ddMMyy");
    }

    @Override
    public Clearing process(LineClearing line) throws Exception {

    	logger.debug("Calculated age ");
    	String[] tab= this.filename.split("\\.");
    	String dd=tab[1];
    
	 
    	
    	Clearing cl=ClearingHelper.getClearingItem(line.getLine());
    	cl.setIndex(line.getNbLine());
    	
    	if(cl.getCodeOperation().equals("27000"))
    		System.out.println("******************* 27000");
    	else
    		System.out.println(cl.getCodeOperation());
    	
    	// pour les date introuvale
    	   Date date=null;
   	    try {
   	    	date=df.parse(dd);
   	    	if(cl.getDateDeProcessing()==null) {
   	    		cl.setDateDeProcessing(date);
   	    	}
   		} catch (Exception e) {
   			// TODO: handle exception
   		}
    	
        return cl;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Processor ended.");
        return ExitStatus.COMPLETED;
    }
}
