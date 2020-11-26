package com.monetique.service;

import java.util.List;

import com.monetique.dto.ItemBatch;
import com.monetique.dto.Jour;
import com.monetique.dto.ResponseCl;
import com.monetique.dto.ResponseClDto;

public interface TraitementClearingBatchService {

	public void removeFile(String filename);

	public ResponseClDto getConsultationCompense(Jour jour) throws Exception;
	public List<String> getListClearingFiles() throws Exception;
	public ResponseCl preIntClearingByFileName(String filename) throws Exception;

	public List<ItemBatch> getIntegClearingFiles(String filename) throws Exception;

	
	public ResponseClDto getConsultationIntegrationImal(Jour jour) throws Exception;

	
}
