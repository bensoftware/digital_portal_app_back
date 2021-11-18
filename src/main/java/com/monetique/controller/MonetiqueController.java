package com.monetique.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.dto.monetique.FileDto;
import com.monetique.model.helper.monetique.ClientCifDtoBinded;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.MonetiqueService;
import com.monetique.um.dto.ResponseDto;

@RestController
@CrossOrigin("*")
public class MonetiqueController {
    
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@Autowired
	ClientCifDtoBinded clientCifDtoBinded;
	
	@Autowired
	MonetiqueService monetiqueService;

	
	
	@PreAuthorize("hasAuthority('createcardmon')")
	@RequestMapping(value="/getClientDataByCif/{cif}", produces ={"application/json"},method=RequestMethod.GET)
	public @ResponseBody ResponseDto getClientDataByCif (@PathVariable String cif) throws Exception {
		//test cif validations format
		com.monetique.dto.monetique.ResponseDto res=null;
			res=monetiqueService.getClientDataByCif(cif);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res);
	}
	

	@PreAuthorize("hasAuthority('createcardmon')")
	@RequestMapping(value ="/uploadFilePdfCreateCard/{cif}", method = RequestMethod.POST)
	public @ResponseBody FileDto uploadFilePdfCreateCard(HttpServletRequest request,@PathVariable String cif) throws Exception {
	
		System.err.println("La valeur du cif : " +cif);
		MultipartHttpServletRequest mRequest;
	    mRequest =(MultipartHttpServletRequest) request;
	    FileDto responseFileDto=null;
	    try {
	    	if(cif!=null)
	    	  responseFileDto= monetiqueService.sendFile(mRequest,cif);
		}catch (Exception e){
			throw new Exception("Digital portal " + e.getMessage());
		}
	    if(responseFileDto==null || responseFileDto.getResponseFileName().length()==0){
	    	throw new Exception("Le nom du fichier est null ");
	    }
		return responseFileDto;
	}
	
	@PreAuthorize("hasAuthority('createcardmon')")
	@RequestMapping(value="/createCarte",method=RequestMethod.POST)
	public @ResponseBody ResponseDto createCarte(@RequestBody ClientCifDto clientCifDto) throws Exception {
		//System.err.println("Image encoded : "+ clientCifDto.getImage());
		System.err.println("File name : "+ clientCifDto.getFilename());
		
		com.monetique.dto.monetique.ResponseDto res=null;
		try {
			res=monetiqueService.createCarte(clientCifDto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res);
	}
	
}
