package com.monetique.service;

import java.util.List;
import com.monetique.dto.AddLiaisonObject;
import com.monetique.dto.Approbation;
import com.monetique.dto.ApprobationResponse;
import com.monetique.dto.Liaison;
import com.monetique.dto.LiaisonIncomplet;
import com.monetique.dto.LiaisonRequest;
import com.monetique.dto.ListLiaisonResponse;
import com.monetique.dto.VerificationMobileRequest;
import com.monetique.dto.VerificationMobileResponse;
import com.monetique.um.dao.entities.Alert;
import com.monetique.um.dao.entities.ExceptionMessage;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dto.VerificationImalResponse;

public interface ILiaisonBankilyService {
	public LiaisonBankily addLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception;
	public List<LiaisonBankily> getAllLiaisonBankily();
	public ListLiaisonResponse getCompteByCif(LiaisonRequest liaisonRequest) throws Exception ;
	public VerificationImalResponse getVerificationImalByCif(String cif) throws Exception ;
	public VerificationImalResponse getVerificationMobileByTelephone(String phone) throws Exception ;
	public LiaisonBankily updateLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception;
	public List<LiaisonBankily> getAllLiaisonBankilyByIdGroup(long idG);
	public ApprobationResponse addApprobation(Approbation approbation) throws Exception ;
	public AddLiaisonObject addLiaison(Liaison liaison) throws Exception ;
	public long getGroupeByUsername(String username) throws Exception ;
	public VerificationImalResponse getUserIdByLogin(String username) throws Exception ;
	public VerificationImalResponse getUserIdByTelephone(String phone) throws Exception ;
	ApprobationResponse addRejet(Approbation approbation) throws Exception;
	public LiaisonBankily updateRejetLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception;
	public LiaisonBankily getLiaisonBankilyByTelephone(String telephone);
	public ExceptionMessage addExceptionMessage(ExceptionMessage exceptionMessage) throws Exception ;
	public Alert addAlert(Alert alert) throws Exception ;
	public VerificationMobileResponse getVerificationMobile(VerificationMobileRequest mobileRequest) throws Exception ;
	public void deleteLiaisonIncompleteMobile(LiaisonIncomplet incomplet) throws Exception ;

}
