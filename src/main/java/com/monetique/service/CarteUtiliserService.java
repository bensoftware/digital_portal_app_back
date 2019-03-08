package com.monetique.service;

import com.monetique.entities.CarteStock;
import com.monetique.entities.Expediteur;

public interface CarteUtiliserService {
	public boolean saveCarteStock(CarteStock c,String senderPhone,String receiverPhone, Expediteur server) throws Exception;
}
