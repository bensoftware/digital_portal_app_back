package com.monetique.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.monetique.helper.TransferHelper;
import com.monetique.service.TransferService;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
@Service
public class TransferServiceImpl implements TransferService{
	
	@Value("${partage.username}")
	String usernamePartage;
	
	@Value("${partage.password}")
	String pwdPartage;
	
	@Value("${partage.ip}")
	String ipPartage;
	

	
	@Value("${environnement}")
	String environnement;
	
	@Override
	public void transferToPartage(String in , String out) throws Exception {

		
		OutputStream destination = null;
		InputStream originalfile = null;
		
		String urlFichierLocalIn= in;
		String urlFichierLocalOut= out;
		
		if(environnement!=null) {
			
			if(environnement.equals("LINUX")) {
				try {
					
					NtlmPasswordAuthentication npa = new NtlmPasswordAuthentication(
							ipPartage, usernamePartage, pwdPartage);
					SmbFile filesmb = new SmbFile(urlFichierLocalOut, npa);
					 destination = filesmb.getOutputStream();
					 originalfile = new FileInputStream(urlFichierLocalIn);
					IOUtils.copy(originalfile, destination);
					originalfile.close();
					destination.close();			
					
				} catch (Exception e) {
					
					originalfile.close();
					destination.close();
					throw new Exception(e.getMessage());
					
					
				}
			}
			else if(environnement.equals("WINDOWS")) {
				TransferHelper.transferFichier(in,out);
	           

			}
			
			
			
		}
		
		
		
		

		
	}


	@Override
	public void createDirToPartage(String urlFichierLocalOut) throws Exception {
		
		
	if(environnement!=null) {
			
			if(environnement.equals("LINUX")) {
				
				try {
					
					NtlmPasswordAuthentication npa = new NtlmPasswordAuthentication(
							ipPartage, usernamePartage, pwdPartage);
					SmbFile filesmb = new SmbFile(urlFichierLocalOut, npa);
					
					if(!filesmb.exists()) {
						filesmb.mkdir();
					}
					// destination = filesmb.getOutputStream();

					
				} catch (Exception e) {
					
					throw new Exception(e.getMessage());
					
					
				}
			}
			else if(environnement.equals("WINDOWS")) {
		          Path pout = Paths.get(urlFichierLocalOut);
		          
		          Files.createDirectory(pout);   
	           

			}
			
			
		}
		
		
	

	}

}
