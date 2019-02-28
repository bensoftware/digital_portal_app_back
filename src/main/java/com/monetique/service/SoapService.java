package com.monetique.service;

import com.monetique.dto.InputSendPhoneCardDto;
import com.monetique.dto.OutputSendPhoneCardDto;

public interface SoapService {

	public OutputSendPhoneCardDto sendPhoneCard(InputSendPhoneCardDto in) throws Exception;
}
