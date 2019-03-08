package com.monetique.service;

import java.util.List;

import com.monetique.dto.ItemInfo;
import com.monetique.entities.CarteStock;
import com.monetique.entities.IntegrationFile;

public interface IntegrationService {

	public List<CarteStock> fileToCarteStock(int operator,String filename) throws Exception;
	public int integrationVoucher(int operator, String filename) throws Exception;

	public List<String> getListVouchers(int operateur) throws Exception;
	public List<IntegrationFile> getHistoriqueIntegration(int operateur) throws Exception;
	public List<ItemInfo> getInfoVoucher(int operator,String filename) throws Exception;
}
