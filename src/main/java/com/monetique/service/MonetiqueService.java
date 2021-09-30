package com.monetique.service;

import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.dto.monetique.ResponseDto;

public interface  MonetiqueService {

	//public ClientCifDto getClientDataByCif(String cif) throws Exception;
	public ResponseDto getClientDataByCif(String cif) throws Exception;

	//public boolean createCarte(ClientCifDto clientCifDto)  throws Exception;
	public ResponseDto createCarte(ClientCifDto clientCifDto)  throws Exception;

}
