package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.ConsultationRecharge;
import com.monetique.entities.CarteStock;
import com.monetique.entities.TypeMontant;
import com.monetique.repositories.CarteStockRepository;
import com.monetique.repositories.TypeMontantRepository;
import com.monetique.service.CarteStockService;
import com.monetique.service.MontantService;
import com.monetique.service.SecuriteService;

@Service
public class CarteStockServiceImpl implements CarteStockService {

	@Autowired
	CarteStockRepository carteStockRepository;
	
	@Autowired
	SecuriteService securiteService;
	
	@Autowired
	MontantService montantService;
	
	@Autowired
	TypeMontantRepository typeMontantRepository;

	@Override
	public int saveCarteStock(CarteStock c,Date date) throws Exception {
		
		
		if(c==null)
			throw new Exception("objet cartestock null");
		
		if(c.getDateExpiration().getTime()<date.getTime())
			return 2;
		
		// set security cryptage
		CarteStock crypt=securiteService.setCryptageCarte(c);
		
		String code= c.getCleNumeroSerie()+c.getNumeroCarte();
		
		
		Optional<CarteStock> opt= carteStockRepository.findById(code);
		
		if(opt.isPresent()) {
			return 1;
		}else {
			c.setId(code);
			crypt=carteStockRepository.save(c);
			if(crypt!=null)
				return 0;
		}
		
		
			
		return 0;
		
	}

	@Override
	public CarteStock getRechargeStock(int operator, double montant) throws Exception {
		// TODO Auto-generated method stub
		
		TypeMontant typeMontant= montantService.getTypeMontant(operator, montant);
		
		CarteStock carteStock= carteStockRepository.findFirstByTypeMontant(typeMontant);
		
		return carteStock;
	}

	@Override
	public boolean deleteRechargeStock(String id) throws Exception {
		
		boolean res= false;
		
		try {
			carteStockRepository.deleteById(id);
			res=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return res;
	}

	@Override
	public List<ConsultationRecharge> getConsultationRechargeStock() throws Exception {

		List<ConsultationRecharge> res= new ArrayList<>();
		
		Iterator<TypeMontant> it= typeMontantRepository.findAll().iterator();
		
		while (it.hasNext()) {
			
			TypeMontant item= it.next();
			double total= carteStockRepository.getTotalRecharge(item.getMontant());
			res.add(new ConsultationRecharge(item.getOperateur().getLibelle(), item.getMontant(), total));
			
		} 
		
		return res;
	}

	
}
