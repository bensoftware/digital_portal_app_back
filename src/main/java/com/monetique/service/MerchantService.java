package com.monetique.service;

import com.monetique.dto.RequestMerchant;
import com.monetique.dto.ResponseMerchant;

public interface MerchantService {

	public  ResponseMerchant  getAllMerchant() throws Exception;
	public  ResponseMerchant  generationReleveMerchant(RequestMerchant req) throws Exception;

}
