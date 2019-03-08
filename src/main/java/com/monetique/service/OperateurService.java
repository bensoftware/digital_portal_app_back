package com.monetique.service;

import java.util.List;

import com.monetique.entities.Operateur;

public interface OperateurService {

	public List<Operateur> getAllOperator() throws Exception;
	public Operateur getOperatorByCode(int code) throws Exception;
}
