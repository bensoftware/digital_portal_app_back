package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.CarteExport;
import com.monetique.entities.CarteItem;
import com.monetique.entities.CarteStock;
import com.monetique.entities.Expiration;
import com.monetique.model.helper.DateHelper;
import com.monetique.repositories.CarteItemRepository;
import com.monetique.repositories.CarteStockRepository;
import com.monetique.repositories.ExpirationRepository;
import com.monetique.service.ExpirationService;

@Service
public class ExpirationServiceImpl implements ExpirationService {

	@Autowired
	ExpirationRepository expirationRepository;
	
	@Autowired
	CarteItemRepository carteItemRepository;
	
	@Autowired
	CarteStockRepository carteStockRepository;


	@Override
	public void saveExport(List<CarteExport> list) throws Exception {

		Expiration expiration=new Expiration();
		expiration.setDateCreation(new Date());
		expiration.setMaj(false);
		expiration.setTotal(list.size());
		
		expiration= expirationRepository.save(expiration);
		
		for(CarteExport x : list) {
			carteItemRepository.save(new CarteItem(x.getNumSerie(), expiration));
		}
		
	}

	@Override
	public List<Expiration> getAllExport() throws Exception {
		// TODO Auto-generated method stub
		return expirationRepository.getAllExpiration();
	}

	@Override
	public void deleteExpiration(long id) throws Exception {
		// TODO Auto-generated method stub
		
		List<CarteItem> list =carteItemRepository.getAllCarteByExp(id);
		
		if(list!=null && list.size()!=0) {
			carteItemRepository.deleteAll(list);;

		}
		
		expirationRepository.deleteById(id);
		
	}

	@Override
	public List<CarteExport> getExport(long id) throws Exception {
		
		List<CarteItem> listItem=carteItemRepository.getAllCarteByExp(id);
		
		List<CarteExport> res= new ArrayList<>();
		
		if(listItem!=null && listItem.size()!=0) {

			for(CarteItem x : listItem) {
				
				CarteStock ct= carteStockRepository.findById(x.getId()).get();
				if(ct!=null) {
					res.add(new CarteExport(ct.getId(), ct.getTypeMontant().getMontant(), ct.getDateExpiration()));

				}
				
			}
			
		}
		

		return res;
	}

	@Override
	public void valideExport(long id,String dateExpiration) throws Exception {
		// TODO Auto-generated method stub
		List<CarteItem> listItem=carteItemRepository.getAllCarteByExp(id);
		
		
		if(listItem!=null && listItem.size()!=0) {

			for(CarteItem x : listItem) {
				
				CarteStock ct= carteStockRepository.findById(x.getId()).get();
				if(ct!=null) {
                   ct.setDateExpiration(DateHelper.getDateFormat(dateExpiration));
                   carteStockRepository.save(ct);
				}
				
			}
			
		}
	    
		
		Expiration ex=expirationRepository.findById(id).get();
		
		if(ex!=null) {
			ex.setMaj(true);
			ex.setDateMaj(new Date());
			expirationRepository.save(ex);
		}
		
	}
	



	
}
