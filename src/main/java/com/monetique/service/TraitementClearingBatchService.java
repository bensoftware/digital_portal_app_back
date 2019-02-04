package com.monetique.service;

import java.util.Date;
import java.util.List;

import com.monetique.entities.Clearing;

public interface TraitementClearingBatchService {

	
	public void verificationClGIMTELDate(Date du, Date au) throws Exception;
	//public void integrationCleationSSBatch() throws Exception;
	
	public void generationClearingFileByCl(List<Clearing> list) throws Exception;
	
	
}
