package com.monetique.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.dto.Carte;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.ReleveMCService;
import com.monetique.um.dto.ResponseDto;

@RestController
public class ReleveMCController {
	@Autowired
	ReleveMCService releveMCService;
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@PreAuthorize ("hasAnyAuthority ('constsoldemcprep', 'genrelevmcprep')")
	@RequestMapping(value="/getInfo/{pan}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getInfo(@PathVariable String pan) throws Exception {
		Carte dto=releveMCService.getInfo(pan);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	@PreAuthorize ("hasAnyAuthority ('constsoldemcprep', 'genrelevmcprep')")
	@RequestMapping(value="/generateRapport/{pan}",method=RequestMethod.GET)
	public @ResponseBody void generateInfoReleve(@PathVariable String pan) throws Exception {
		
		releveMCService.generateInfoReleve(pan,httpServletResponse);
	}
	@PreAuthorize ("hasAnyAuthority ('constsoldemcprep', 'genrelevmcprep')")
	@RequestMapping(value="/generateRapport/{pan}/{dateDu}/{dateAu}",method=RequestMethod.GET)
	public @ResponseBody void generateInfoBetwwenDateReleve(@PathVariable String pan,@PathVariable long dateDu,@PathVariable long dateAu) throws Exception {
		releveMCService.generateInfoBetwwenDateReleve(pan,dateDu,dateAu,httpServletResponse);
	}

}
