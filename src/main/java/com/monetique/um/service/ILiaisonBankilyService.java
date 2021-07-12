package com.monetique.um.service;

import java.util.List;

import com.monetique.dto.LiaisonRequest;
import com.monetique.dto.ListLiaisonResponse;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dto.VerificationImalResponse;

public interface ILiaisonBankilyService {
	public LiaisonBankily addLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception;
	public List<LiaisonBankily> getAllLiaisonBankily();
	public ListLiaisonResponse getCompteByCif(LiaisonRequest liaisonRequest) throws Exception ;
	public VerificationImalResponse getVerificationImalByCif(String cif) throws Exception ;
	public VerificationImalResponse getVerificationMobileByTelephone(String phone) throws Exception ;

}
