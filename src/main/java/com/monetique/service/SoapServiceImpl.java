package com.monetique.service;


import org.springframework.stereotype.Service;

import com.monetique.dto.InputSendPhoneCardDto;
import com.monetique.dto.OutputSendPhoneCardDto;
import com.monetique.dto.Status;

@Service
public class SoapServiceImpl implements SoapService {

	@Override
	public OutputSendPhoneCardDto sendPhoneCard(InputSendPhoneCardDto in) throws Exception {
		// TODO Auto-generated method stub
		
		OutputSendPhoneCardDto out = new OutputSendPhoneCardDto();
		
		int codeStatus=0;
		int codeError=0;
		
		if(in.username.equals("mbanking") && in.password.equals("mbanking123@")) {
			if(in.amount==1251) {
				codeStatus=1;
				codeError=100;
			}
			else if(in.operator!=1 && in.operator!=2) {
				codeStatus=1;
				codeError=102;
			}
			else if(in.amount==500) {
				codeStatus=1;
				codeError=103;
			}
			else if(in.serverSender!=1 && in.serverSender!=2) {
				codeStatus=1;
				codeError=104;
			}
		}
		else {
			codeStatus=1;
			codeError=101;
		}
		
	
		
		
		
		out.setCode("114712001455700114");
		Status s = new Status();
		s.setCodestatus(codeStatus);
		s.setCodeerror(codeError);
		out.setStatus(s);
		
		return out;
	}


}
