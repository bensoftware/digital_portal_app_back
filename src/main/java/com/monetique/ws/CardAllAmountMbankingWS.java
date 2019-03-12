package com.monetique.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monetique.dto.InputGetAllAmountCardDto;
import com.monetique.dto.OutputGetAllAmountCardDto;
import com.monetique.service.SoapService;


@Component
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CardAllAmountMbankingWS {

	@Autowired
	SoapService soapService;
	
	
	@WebMethod
	@WebResult(name = "getallamount")
	public OutputGetAllAmountCardDto getallamount(@WebParam(name="inputgetamounts") InputGetAllAmountCardDto in) throws Exception {

		return soapService.getAllAmount(in);
	}
	
	
} 
