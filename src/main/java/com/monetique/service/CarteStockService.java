package com.monetique.service;

import java.util.Date;
import java.util.List;

import com.monetique.dto.ConsultationRecharge;
import com.monetique.entities.CarteStock;

public interface CarteStockService {

	public List<ConsultationRecharge> getConsultationRechargeStock() throws Exception;
	public int saveCarteStock(CarteStock c,Date date) throws Exception;
	public boolean deleteRechargeStock(String id) throws Exception;
	public CarteStock getRechargeStock(int operator,double montant) throws Exception;
}
