package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.CarteExport;
import com.monetique.dto.ConsultationRecharge;
import com.monetique.entities.CarteStock;
import com.monetique.entities.TypeMontant;
import com.monetique.model.helper.CarteHelper;
import com.monetique.repositories.CarteStockRepository;
import com.monetique.repositories.TypeMontantRepository;
import com.monetique.service.CarteStockService;
import com.monetique.service.MontantService;
import com.monetique.service.ParametreService;
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
	
	@Autowired
	ParametreService parametreService;
	
	
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
			double total= carteStockRepository.getTotalRecharge(item.getId());
			
			Date dateEx= carteStockRepository.findFirstByTypeMontantOrderByDateExpirationAsc(item).getDateExpiration();

			// stock 
			boolean epuisement= montantService.checkEpuisementMontantItem(item.getId());
			
			// expiration
			boolean expiration=checkExpirationDate(dateEx);
			
			res.add(new ConsultationRecharge(item.getOperateur().getLibelle(), item.getMontant(), total,dateEx,epuisement,expiration));
			
		} 
		
		return res;
	}
	
	@Override
	public Map<TypeMontant, Integer> getExpiration(int expiration) throws Exception {
		// TODO Auto-generated method stub
		Map<TypeMontant, Integer> res= new HashMap<>();
		
		Calendar ac= Calendar.getInstance();
		ac.setTime(new Date());
		// incrementation par mois
		ac.add(Calendar.MONTH, expiration); 
		
		Date dateEx=ac.getTime();
		
		List<CarteStock> carteExpires= carteStockRepository.getAllStockExpiration(dateEx);
		
		for(CarteStock x : carteExpires) {
			TypeMontant t= x.getTypeMontant();
			if(res.containsKey(t)) {
				Integer cpt=res.get(t);
				cpt++;
				res.replace(t, cpt);
				
			}else {
				res.put(t, 1);
			}
		}
		
		return res;
	}

	@Override
	public boolean checkExpirationDate(Date dateEx) throws Exception {
		// TODO Auto-generated method stub

		int expiration = parametreService.getExpiration();
		
		return CarteHelper.getExpirationyCarteStock(dateEx, expiration);
	}

	@Override
	public List<CarteExport> getAllExpiration() throws Exception {
		
		List<CarteExport> res= new ArrayList<>();
		
		int expiration= parametreService.getExpiration();
		
		Calendar ac= Calendar.getInstance();
		ac.setTime(new Date());
		// incrementation par mois
		ac.add(Calendar.MONTH, expiration); 
		
		Date dateEx=ac.getTime();
		
		List<CarteStock> carteExpires= carteStockRepository.getAllStockExpiration(dateEx);
	
		for(CarteStock x : carteExpires) {
			res.add(new CarteExport(x.getId(), x.getTypeMontant().getMontant(), x.getDateExpiration()));
		}
		
		return res;
	}

	
}
