package com.monetique.service;

import java.util.Date;
import java.util.List;

import com.monetique.dto.CarteExport;
import com.monetique.entities.Expiration;

public interface ExpirationService {
	
	public void saveExport(List<CarteExport> list) throws Exception;
	public List<Expiration> getAllExport() throws Exception;
	
	public void deleteExpiration(long id)  throws Exception;
	
	public List<CarteExport> getExport(long id)throws Exception;
	
	public void valideExport(long id,String dateExpiration)throws Exception;
}
