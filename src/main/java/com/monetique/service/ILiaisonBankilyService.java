package com.monetique.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.monetique.dto.AddLiaisonObject;
import com.monetique.dto.Approbation;
import com.monetique.dto.ApprobationResponse;
import com.monetique.dto.Liaison;
import com.monetique.dto.LiaisonIncomplet;
import com.monetique.dto.LiaisonRequest;
import com.monetique.dto.ListLiaisonResponse;
import com.monetique.dto.ResponseDto;
import com.monetique.dto.VerificationMobileRequest;
import com.monetique.dto.VerificationMobileResponse;
import com.monetique.um.dao.entities.Alert;
import com.monetique.um.dao.entities.ExceptionMessage;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dao.entities.Params;
import com.monetique.um.dto.VerificationImalResponse;

public interface ILiaisonBankilyService {
	
	public LiaisonBankily addLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception;
	public List<LiaisonBankily> getAllLiaisonBankily();
	public ListLiaisonResponse getCompteByCif(LiaisonRequest liaisonRequest)  ;
	public VerificationImalResponse getVerificationImalByCif(String cif) throws Exception ;
	public VerificationImalResponse getVerificationMobileByTelephone(String phone) throws Exception ;
	public LiaisonBankily updateLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception;
	public List<LiaisonBankily> getAllLiaisonBankilyByIdGroup(long idG);
	public ApprobationResponse addApprobation(Approbation approbation) throws Exception ;
	public AddLiaisonObject addLiaison(Liaison liaison) throws Exception ;
	public long getGroupeByUsername(String username) throws Exception ;
	public VerificationImalResponse getUserIdByLogin(String username) throws Exception ;
	public VerificationImalResponse getUserIdByTelephone(String phone) throws Exception ;
	public ApprobationResponse addRejet(Approbation approbation) throws Exception;
	public LiaisonBankily updateRejetLiaisonBankily(LiaisonBankily liaisonBankily) throws Exception;
	public LiaisonBankily getLiaisonBankilyByTelephone(String telephone);
	public ExceptionMessage addExceptionMessage(ExceptionMessage exceptionMessage) throws Exception ;
	public Alert addAlert(Alert alert) throws Exception ;
	public VerificationMobileResponse getVerificationMobile(VerificationMobileRequest mobileRequest) throws Exception ;
	public void deleteLiaisonIncompleteMobile(LiaisonIncomplet incomplet) throws Exception ;
	public void uploadFile(HttpServletRequest request,long id) throws Exception;
	public ResponseDto uploadFileAutomatique(HttpServletRequest request,String cif,String nni,String telephone) throws Exception;
	public void updateDocLiaison(String doc,long id);
	public void generationPdf(HttpServletResponse resonse,String fileName) throws IOException;
	public void generateAllLiaisonQuotidient() throws Exception;
	public Set<String> generateAllLiaisonLocal() throws Exception;
	public List<LiaisonBankily> getAllLiaisonBankilyApprove(Date debut,Date fin);
	public Params updateParams(Params params) throws Exception ;
	public void sendToPartage(String prefix,String filename) throws Exception;

}
