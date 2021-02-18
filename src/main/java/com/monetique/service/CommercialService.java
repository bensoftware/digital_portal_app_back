package com.monetique.service;

import com.monetique.dto.Client;
import com.monetique.dto.OtpIn;
import com.monetique.dto.OtpOut;
import com.monetique.dto.OtpResponse;



public interface CommercialService {
	
	public OtpOut getOtp(OtpIn in) throws Exception;
	public Client getPartyIdByTelephone(String telephone) throws Exception;
	public String getCifByTelephone(String telephone) throws Exception;
	public Client getCifByPartyId(String telephone) throws Exception;
	public Client getClientDetailsByNni(String nni) throws Exception;
	
	public Client getClientDetailsByPhone(String phone) throws Exception;
	
	public Client getClientDetailsByCif(String cif) throws Exception;
	public boolean deblockByPhone(String phone) throws Exception;
	public boolean resetAttempt(String phone) throws Exception;
	public String resendPinTemporaire(String phone) throws Exception;
}
