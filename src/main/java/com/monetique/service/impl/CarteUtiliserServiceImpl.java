package com.monetique.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.dto.CarteItem;
import com.monetique.dto.ConsultationRecharge;
import com.monetique.entities.CarteStock;
import com.monetique.entities.CarteUtilise;
import com.monetique.entities.Expediteur;
import com.monetique.entities.TypeMontant;
import com.monetique.model.helper.CarteHelper;
import com.monetique.repositories.CarteUtiliseRepository;
import com.monetique.repositories.ExpediteurRepository;
import com.monetique.repositories.TypeMontantRepository;
import com.monetique.service.CarteUtiliserService;

@Service
@Transactional
public class CarteUtiliserServiceImpl implements CarteUtiliserService {

	@Autowired
	CarteUtiliseRepository carteUtiliseRepository;
	
	@Autowired
	TypeMontantRepository typeMontantRepository;
	
	@Autowired
	ExpediteurRepository expediteurRepository;


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


	@Override
	public List<ConsultationRecharge> getConsultationRechargeUtilise() throws Exception {
		
	List<ConsultationRecharge> res= new ArrayList<>();
		
		Iterator<TypeMontant> it= typeMontantRepository.findAll().iterator();
		
		String mb= expediteurRepository.findById(1).get().getLibelle();
		String gab= expediteurRepository.findById(2).get().getLibelle();
		
		while (it.hasNext()) {
			
			TypeMontant item= it.next();
			double totalMb= carteUtiliseRepository.getTotalRechUtiliseByExp(item.getMontant(),1);
			double totalGab= carteUtiliseRepository.getTotalRechUtiliseByExp(item.getMontant(),2);
			
			if(totalMb>0) {
				res.add(new ConsultationRecharge(item.getOperateur().getLibelle(), item.getMontant(), totalMb,mb));

			}
            if(totalGab>0) {
    			res.add(new ConsultationRecharge(item.getOperateur().getLibelle(), item.getMontant(), totalGab,gab));

			}
			
		} 
		
		return res;
		
	}


	@Override
	public List<CarteItem> getRechercheRecharge(int type, String recherche) throws Exception {
		
		List<CarteUtilise> res=null;
		List<CarteItem> items=new ArrayList<>();
		switch (type) {
		  
		case 1:
			res=carteUtiliseRepository.getConsommeByCode(recherche);
			break;
        case 2:
			res=carteUtiliseRepository.getConsommeByCleSerie(recherche);

			break;
        case 3:
			res=carteUtiliseRepository.getConsommeByNumeroCarte(recherche);

			break;
        case 4:
			res=carteUtiliseRepository.getConsommeByTelephonePorteur(recherche);

		break;
        case 5:
			res=carteUtiliseRepository.getConsommeByTelephoneBeneficiaire(recherche);

		break;
		default:
			break;
		}
		
		if(res!=null) {
			for(CarteUtilise x : res) {
				items.add(new CarteItem(x.getId(), x.getCleNumeroSerie(), x.getNumeroCarte(), x.getTelephonePorteur(), x.getTelephoneBeneficiaire(), x.getTypeMontant().getMontant()));
			}
		}
		
		return items; 
	}

	
	
}
