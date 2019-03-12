package com.monetique.service;

import java.util.List;

import com.monetique.dto.CarteItem;
import com.monetique.dto.ConsultationRecharge;
import com.monetique.entities.CarteStock;
import com.monetique.entities.CarteUtilise;
import com.monetique.entities.Expediteur;

public interface CarteUtiliserService {
	
	public List<ConsultationRecharge> getConsultationRechargeUtilise() throws Exception;
	
	public List<CarteItem> getRechercheRecharge(int type,String recherche) throws Exception;
	
	public boolean saveCarteStock(CarteStock c,String senderPhone,String receiverPhone, Expediteur server) throws Exception;
}
