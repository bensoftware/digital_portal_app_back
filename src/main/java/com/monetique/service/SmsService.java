package com.monetique.service;

public interface SmsService {

	public int sendSms(String msg,String phone) throws Exception;
	
	
//	public int sendSmsSuperviseur(String msg,long id) throws Exception;
	
}
