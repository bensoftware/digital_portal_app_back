package com.monetique.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.monetique.entities.Parametrage;
import com.monetique.helper.MediaTypeUtils;
import com.monetique.repositories.ParametrageRepository;


@RestController
@Transactional
@CrossOrigin("*")
public class UploadController {

	
	@Autowired
	ParametrageRepository parametrageRepository;
	
	@Autowired
    private ServletContext servletContext;
	
	//@PreAuthorize("hasAuthority('genReleve')")
    @GetMapping("/generationPdf/{fileName}")
    public void generationPdf(HttpServletResponse resonse,@PathVariable String fileName) throws IOException {
        org.springframework.http.MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        File file = new File(Paths.get(System.getProperty("user.home")) + "/releve/" + fileName);
        resonse.setContentType(mediaType.getType());
        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());
        resonse.setContentLength((int) file.length());
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }


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
