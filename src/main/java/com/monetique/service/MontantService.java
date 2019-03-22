package com.monetique.service;

import java.util.List;

import com.monetique.entities.MontantNotification;
import com.monetique.entities.TypeMontant;

public interface MontantService {

	public List<MontantNotification> checkEpuisementMontant(int epuisement) throws Exception;

	public boolean checkEpuisementMontantItem(long id) throws Exception;

	
	public List<Double> getListByOperator(int operator) throws Exception;
    public boolean checkAmount(int operator,double montant) throws Exception;
    public TypeMontant getTypeMontant(int operator,double montant) throws Exception;
}
