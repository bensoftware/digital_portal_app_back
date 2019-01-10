package com.monetique.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import com.monetique.entities.Clearing;
import com.monetique.model.helper.ClearingHelper;

public class LineProcessor implements ItemProcessor<String, Clearing>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(LineProcessor.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Line Processor initialized.");
    }

    @Override
    public Clearing process(String line) throws Exception {

    	logger.debug("Calculated age ");
        return ClearingHelper.getClearingItem(line);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Line Processor ended.");
        return ExitStatus.COMPLETED;
    }
}
