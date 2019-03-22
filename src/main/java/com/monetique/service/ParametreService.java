package com.monetique.service;

import com.monetique.entities.Parametrage;

public interface ParametreService {

	public int getEpuisement() throws Exception;
	public int getExpiration() throws Exception;
	
	public Parametrage getParameter() throws Exception;
	public Parametrage setParameter(Parametrage p) throws Exception;
}
