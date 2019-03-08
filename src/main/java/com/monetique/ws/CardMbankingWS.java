package com.monetique.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monetique.dto.InputGetAllAmountCardDto;
import com.monetique.dto.InputSendPhoneCardDto;
import com.monetique.dto.OutputGetAllAmountCardDto;
import com.monetique.dto.OutputSendPhoneCardDto;
import com.monetique.service.SoapService;


@Component
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CardMbankingWS {

	@Autowired
	SoapService soapService;
	
	
	@WebMethod
	@WebResult(name = "sendrecharge")
	public OutputSendPhoneCardDto sendrecharge(@WebParam(name="inputrecharge") InputSendPhoneCardDto in) throws Exception {

		return soapService.sendPhoneCard(in);
	}
	
	@WebMethod
	@WebResult(name = "getallamount")
	public OutputGetAllAmountCardDto getallamount(@WebParam(name="inputgetamounts") InputGetAllAmountCardDto in) throws Exception {

		return soapService.getAllAmount(in);
	}
	
	
} 
