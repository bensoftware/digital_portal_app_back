package com.monetique.service;

import com.monetique.entities.CarteStock;

public interface SecuriteService {

	public CarteStock setCryptageCarte(CarteStock c) throws Exception;
	public CarteStock setDecryptageCarte(CarteStock c) throws Exception;
	public String setCryptageLigne(String ligne);
//	public String setDecryptageLigne(String ligne);
}
