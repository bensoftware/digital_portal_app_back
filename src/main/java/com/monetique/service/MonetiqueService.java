package com.monetique.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.dto.monetique.ResponseDto;
import com.monetique.dto.monetique.FileDto;

public interface  MonetiqueService {

	//public ClientCifDto getClientDataByCif(String cif) throws Exception;
	public ResponseDto getClientDataByCif(String cif) throws Exception;

	//public boolean createCarte(ClientCifDto clientCifDto)  throws Exception;
	public ResponseDto createCarte(ClientCifDto clientCifDto)  throws Exception;
	
	public FileDto sendFile(MultipartHttpServletRequest MultipartHttpServletRequest,String cif)  throws Exception;
	
	
	

}
