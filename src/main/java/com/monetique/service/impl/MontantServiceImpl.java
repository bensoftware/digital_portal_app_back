package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.MontantNotification;
import com.monetique.entities.TypeMontant;
import com.monetique.repositories.CarteStockRepository;
import com.monetique.repositories.TypeMontantRepository;
import com.monetique.service.MontantService;

@Service
public class MontantServiceImpl implements MontantService {

	@Autowired
	TypeMontantRepository montantRepository;
	
	@Autowired
	CarteStockRepository carteStockRepository;
	
	@Override
	public List<Double> getListByOperator(int operator) throws Exception {
		// TODO Auto-generated method stub
		return montantRepository.getAllMontantByOperateur(operator);
	}

	@Override
	public boolean checkAmount(int operator, double montant) throws Exception {
		// TODO Auto-generated method stub
		boolean res= false;
		
		TypeMontant typeMontant= montantRepository.getExistMontantByOperateur(montant, operator);
		if(typeMontant!=null)
			res=true;
		
		return res;
	}

	@Override
	public TypeMontant getTypeMontant(int operator, double montant) throws Exception {
		// TODO Auto-generated method stub
		return montantRepository.getExistMontantByOperateur(montant, operator);
	}

	@Override
	public List<MontantNotification> checkEpuisementMontant(int epuisement) throws Exception {
		
		List<MontantNotification> res= new ArrayList<>();
		
		Iterator<TypeMontant> allMontant= montantRepository.findAll().iterator();
		
		while (allMontant.hasNext()) {
			TypeMontant item=allMontant.next();
			double stock= carteStockRepository.getTotalRecharge(item.getMontant());
			
			if(stock<=epuisement) {
				res.add(new MontantNotification((int) stock, item,null));
			}
		}
		
		return res;
	}




}
