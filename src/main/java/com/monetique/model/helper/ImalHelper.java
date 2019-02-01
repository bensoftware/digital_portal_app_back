package com.monetique.model.helper;

import java.io.IOException;

import mr.bpm.mbanking.ws.Imal;

public class ImalHelper {

	
	public static com.monetique.entities.Imal getImalEntity(Imal i) throws IOException {
		
		com.monetique.entities.Imal m= new com.monetique.entities.Imal();

		m.setMontant(i.getMontant());
		m.setRrn(i.getRrn());
		m.setType(i.getType());
		m.setDate(i.getDate().toGregorianCalendar().getTime());
		
	    return m;
		 }
	
	
	
	}
	
	

