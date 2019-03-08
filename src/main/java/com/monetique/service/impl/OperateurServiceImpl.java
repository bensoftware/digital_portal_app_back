package com.monetique.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.Operateur;
import com.monetique.repositories.OperateurRepository;
import com.monetique.service.OperateurService;

@Service
public class OperateurServiceImpl implements OperateurService {

	@Autowired
	OperateurRepository operateurRepository;
	
	@Override
	public List<Operateur> getAllOperator() throws Exception {
		// TODO Auto-generated method stub
		return operateurRepository.getAllOperator();
	}

	@Override
	public Operateur getOperatorByCode(int code) throws Exception {
		// TODO Auto-generated method stub
		Optional<Operateur> opt= operateurRepository.findById(code);
		
		Operateur operateur=null;
		
		if(!opt.isPresent())
			throw new Exception("operateur innexistant");
		else
			operateur = opt.get();
		return operateur;
	}

	
}
