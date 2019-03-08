package com.monetique.service;

import java.util.Date;

import com.monetique.entities.CarteStock;

public interface CarteStockService {

	public int saveCarteStock(CarteStock c,Date date) throws Exception;
	public boolean deleteRechargeStock(String id) throws Exception;
	public CarteStock getRechargeStock(int operator,double montant) throws Exception;
}
