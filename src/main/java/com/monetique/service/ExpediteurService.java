package com.monetique.service;

import java.util.List;

import com.monetique.entities.Expediteur;

public interface ExpediteurService {

	public List<Expediteur>	getAllExpediteur() throws Exception;
	public Expediteur getExpediteurByCode(int code) throws Exception;
}
