package com.monetique.service;

import com.monetique.dto.SoldeDetails;

public interface MauritelService {


	public SoldeDetails getSoldeByLineNumberAndProductType(String custCode,String productType)  throws Exception ;
	
	
}
