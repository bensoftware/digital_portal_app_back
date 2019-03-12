package com.monetique.service;

import java.util.List;

import com.monetique.dto.IntegrationExcpItem;
import com.monetique.dto.ItemInfo;
import com.monetique.entities.CarteStock;
import com.monetique.entities.IntegrationFile;

public interface IntegrationService {

	public List<CarteStock> fileToCarteStock(int operator,String filename) throws Exception;
	public int integrationVoucher(int operator, String filename) throws Exception;

	public List<String> getListVouchers(int operateur) throws Exception;
	public List<IntegrationFile> getHistoriqueIntegration(int operateur) throws Exception;
	public List<IntegrationFile> getHistoriqueIntegrationException(int operateur) throws Exception;
	public List<ItemInfo> getInfoVoucher(int operator,String filename) throws Exception;
	
	public List<IntegrationExcpItem> getExceptionIntegByHisto(long id) throws Exception;

	
	public List<IntegrationExcpItem> getExceptionByOp(int operateur) throws Exception;

}
