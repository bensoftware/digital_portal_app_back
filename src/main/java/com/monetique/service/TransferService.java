package com.monetique.service;

public interface TransferService {

	public void transferToPartage(String in , String out) throws Exception;
	public void createDirToPartage(String out) throws Exception;

}
