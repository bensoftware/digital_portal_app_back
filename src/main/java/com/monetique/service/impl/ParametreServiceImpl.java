package com.monetique.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.entities.Parametrage;
import com.monetique.repositories.ParametrageRepository;
import com.monetique.service.ParametreService;

@Service
public class ParametreServiceImpl implements ParametreService {

	
	@Autowired
	ParametrageRepository parametrageRepository;
	

	

	@Override
	public Parametrage getParameter() throws Exception {
		
    Optional<Parametrage> opt= parametrageRepository.findById(1L);
		
	    if(opt.isPresent()) {
	    	Parametrage parametrage=opt.get();
	    	return parametrage;
	    
	    }
	    
		
		return null;
		
	}


	@Override
	public Parametrage setParameter(Parametrage p) throws Exception {
    Optional<Parametrage> opt= parametrageRepository.findById(1L);
		
	    if(opt.isPresent() && p.getId()==1) {
	    	
	    	return parametrageRepository.save(p);
	    	
	    }
	    
		return null;
	}
	
	


}
