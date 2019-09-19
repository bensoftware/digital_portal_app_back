package com.monetique.service;

import java.util.Date;
import java.util.List;

import com.monetique.dto.ItemBatch;
import com.monetique.dto.ResponseCl;
import com.monetique.dto.ResponseClDto;

public interface TraitementClearingBatchService {

	public void removeFile(String filename);

	public ResponseClDto getConsultationCompense(Date date) throws Exception;
	public List<String> getListClearingFiles() throws Exception;
	public ResponseCl preIntClearingByFileName(String filename) throws Exception;

	public List<ItemBatch> getIntegClearingFiles(String filename) throws Exception;

	
}
