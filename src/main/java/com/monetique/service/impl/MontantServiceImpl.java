package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.ItemTypeRecharge;
import com.monetique.entities.MontantNotification;
import com.monetique.entities.TypeMontant;
import com.monetique.repositories.CarteStockRepository;
import com.monetique.repositories.TypeMontantRepository;
import com.monetique.service.MontantService;
import com.monetique.service.ParametreService;

@Service
public class MontantServiceImpl implements MontantService {

	@Autowired
	TypeMontantRepository montantRepository;
	
	@Autowired
	CarteStockRepository carteStockRepository;
	
	@Autowired
	ParametreService parametreService;
	
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
		
		List<TypeMontant> allMontant= montantRepository.getAllMontant();
		
		if(allMontant!=null && allMontant.size()!=0)
		for (TypeMontant item : allMontant) {
			double stock= carteStockRepository.getTotalRecharge(item.getId());
			
			if(item.isActiveStock()) {
				epuisement=item.getStock();
			}
			
			if(stock<=epuisement) {
				res.add(new MontantNotification((int) stock, item,null));
			}
		}
		
		return res;
	}

	@Override
	public boolean checkEpuisementMontantItem( long id) throws Exception {

		Optional<TypeMontant> opt= montantRepository.findById(id);
		
		if(opt.isPresent()) {
			
			int epuisement=parametreService.getEpuisement();
			
			TypeMontant typeMontant=opt.get();
			
			double stock= carteStockRepository.getTotalRecharge(typeMontant.getId());

			if(typeMontant.isActiveStock()) {
				epuisement=typeMontant.getStock();
			}
			
			if(stock<=epuisement) {
				return true;
			}

			
		}
		
		return false;

	}

	@Override
	public List<ItemTypeRecharge> getAllRecharge() throws Exception {

		List<ItemTypeRecharge> res= new ArrayList<>();
		
		List<TypeMontant> list= montantRepository.getAllMontantTri();
		
		if(list!=null && list.size()!=0)
		for(TypeMontant m :list) {
			res.add(new ItemTypeRecharge(m.getId(),m.getOperateur().getLibelle(), m.getMontant(), m.isActiveStock(), m.isActive(), m.getStock()));

		}
		
		return res;
	}

	@Override
	public TypeMontant getTypeRecharge(long id) throws Exception {
		// TODO Auto-generated method stub
		return montantRepository.findById(id).get();
	}

	@Override
	public TypeMontant setTypeRecharge(ItemTypeRecharge c) throws Exception {
		// TODO Auto-generated method stub
		TypeMontant montant=null;
		
		Optional<TypeMontant> opt= montantRepository.findById(c.getId());
		
		if(!opt.isPresent()) 
			throw new Exception("montant inexistant");
		
		montant=opt.get();
		
		montant.setActive(c.isActive());
		montant.setActiveStock(c.isStockPerso());
		montant.setStock(c.getSeuil());
		
		return montantRepository.save(montant);
	}




}
