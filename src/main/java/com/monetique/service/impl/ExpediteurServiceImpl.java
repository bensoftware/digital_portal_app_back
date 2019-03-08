package com.monetique.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.Expediteur;
import com.monetique.repositories.ExpediteurRepository;
import com.monetique.service.ExpediteurService;

@Service
public class ExpediteurServiceImpl implements ExpediteurService {

	@Autowired
	ExpediteurRepository expediteurRepository;
	
	@Override
	public List<Expediteur> getAllExpediteur() throws Exception {
		
	     return	expediteurRepository.getAllExpediteur();
	}

	@Override
	public Expediteur getExpediteurByCode(int code) throws Exception {
		// TODO Auto-generated method stub
		Optional<Expediteur> opt= expediteurRepository.findById(code);
		return opt.get();
	}


}
