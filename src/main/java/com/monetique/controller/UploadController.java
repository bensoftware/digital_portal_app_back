package com.monetique.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.monetique.entities.Parametrage;
import com.monetique.repositories.ParametrageRepository;


@RestController
@Transactional
@CrossOrigin("*")
public class UploadController {

	
	@Autowired
	ParametrageRepository parametrageRepository;
	

	@PreAuthorize("hasAuthority('intclg')")
	@RequestMapping(value = "/uploadFile" , method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request) throws Exception {

	    //org.springframework.web.multipart.MultipartHttpServletRequest
	    MultipartHttpServletRequest mRequest;
	    mRequest = (MultipartHttpServletRequest) request;

	    Parametrage p=parametrageRepository.findById(1L).get();
	    
	    
		  String directory =p.getUrlInGimtel();
	 
		   
		    String lien="";
		   
		    String filepath = Paths.get(directory,lien).toString();
		    System.out.println("nom : "+filepath);
	   
		    Iterator<String> itr = mRequest.getFileNames();
	        
		    if (itr.hasNext()) {

		    
		    	MultipartFile mFile = mRequest.getFile(itr.next());
	        
		    	String fileName = mFile.getOriginalFilename();
	   	   
		    	
		    	if(!fileName.matches("BCLEAR\\.[0-9]{6}\\.[0-9]+\\.[0-9]+")) {
		               
		    		 throw new Exception("fichier invalide");
		    		}    	
		    	
		    	

		    	File file = new File(directory+"/"+fileName);
			    try {
					FileCopyUtils.copy(mFile.getBytes(),file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    	    }
	}	
}
