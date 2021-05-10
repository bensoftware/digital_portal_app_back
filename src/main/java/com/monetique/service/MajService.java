package com.monetique.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.monetique.dto.Historique;
import com.monetique.dto.RequestDto;
import com.monetique.dto.ResponseDto;

public interface MajService {


	public ResponseDto verify(RequestDto request) throws Exception;
	
	public ResponseDto updateCompteBankily(RequestDto request,HttpServletRequest req) throws Exception;
	
	public List<Historique> getAllHistorique() throws Exception;
	
	

}
