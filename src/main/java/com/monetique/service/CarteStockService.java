package com.monetique.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.monetique.dto.CarteExport;
import com.monetique.dto.ConsultationRecharge;
import com.monetique.entities.CarteStock;
import com.monetique.entities.TypeMontant;

public interface CarteStockService {

	public Map<TypeMontant, Integer> getExpiration(int expiration) throws Exception;
	public List<ConsultationRecharge> getConsultationRechargeStock() throws Exception;
	public int saveCarteStock(CarteStock c,Date date) throws Exception;
	public boolean deleteRechargeStock(String id) throws Exception;
	public CarteStock getRechargeStock(int operator,double montant) throws Exception;

	public boolean checkExpirationDate(Date dateEx) throws Exception;
	
	public List<CarteExport> getAllExpiration() throws Exception;
	
}
