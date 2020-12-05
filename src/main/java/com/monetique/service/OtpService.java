package com.monetique.service;

import com.monetique.dto.OtpIn;
import com.monetique.dto.OtpOut;

public interface OtpService {
	
	public OtpOut getOtp(OtpIn in) throws Exception;

}
