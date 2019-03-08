package com.monetique.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.CarteStock;
import com.monetique.entities.CarteUtilise;
import com.monetique.entities.Expediteur;
import com.monetique.model.helper.CarteHelper;
import com.monetique.repositories.CarteUtiliseRepository;
import com.monetique.service.CarteUtiliserService;

@Service
@Transactional
public class CarteUtiliserServiceImpl implements CarteUtiliserService {

	@Autowired
	CarteUtiliseRepository carteUtiliseRepository;


	@Override
	public boolean saveCarteStock(CarteStock c, String senderPhone, String receiverPhone, Expediteur server)
			throws Exception {
		
		CarteUtilise cu= CarteHelper.getCarteUtiliserByCarteStock(c);
		
		cu.setTelephonePorteur(senderPhone);
		cu.setTelephoneBeneficiaire(receiverPhone);
		cu.setExpediteur(server);
		
		cu= carteUtiliseRepository.save(cu);
		
		return cu!=null?true:false;
	}

	
	
}
