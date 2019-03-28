package com.monetique.service;

import com.monetique.dto.InputGetAllAmountCardDto;
import com.monetique.dto.InputSendPhoneCardDto;
import com.monetique.dto.OutputGetAllAmountCardDto;
import com.monetique.dto.OutputSendPhoneCardDto;

public interface SoapService {

		
	public OutputSendPhoneCardDto sendPhoneCard(InputSendPhoneCardDto in) throws Exception;
	public OutputGetAllAmountCardDto getAllAmount(InputGetAllAmountCardDto in) throws Exception;
}
