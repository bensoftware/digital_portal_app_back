package com.monetique.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.model.helper.ImalHelper;
import com.monetique.repositories.ImalRepository;

import mr.bpm.mbanking.ws.Imal;
import mr.bpm.mbanking.ws.ImalWS;
import mr.bpm.mbanking.ws.ImalWSService;
import mr.bpm.mbanking.ws.ListImal;

@Service
public class ImalServiceImpl implements ImalService {

	@Autowired
	ImalRepository imalRepository;

	
	
	@Override
	public void integration() throws Exception {
		
		
		ImalWSService b =new ImalWSService();
		ImalWS service =b.getImalWSPort();
		

		
		ListImal lmAut= service.imalOUt();
		List<Imal> listClSS= lmAut.getList();

		int cp=1;
		for(Imal x: listClSS) {
			com.monetique.entities.Imal c= ImalHelper.getImalEntity(x);
			imalRepository.save(c);
			System.out.println(cp);
			cp++;
		}
		
	}
	


}
