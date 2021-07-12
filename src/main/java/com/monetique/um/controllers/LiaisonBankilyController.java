package com.monetique.um.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.dto.LiaisonRequest;
import com.monetique.dto.ListLiaisonResponse;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dto.ResponseDto;
import com.monetique.um.dto.VerificationImalResponse;
import com.monetique.um.service.ILiaisonBankilyService;

@RestController
public class LiaisonBankilyController {
	@Autowired
	private ILiaisonBankilyService iLiaisonBankilyService;
	@Autowired
	HttpServletResponse  httpServletResponse;
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/addLiaisonBankily",method= RequestMethod.POST)
	//@PostMapping("/addLiaisonBankily")
	public LiaisonBankily addLiaisonBankily(@RequestBody LiaisonBankily liaisonBankily) throws Exception {
		return iLiaisonBankilyService.addLiaisonBankily(liaisonBankily);
	}
	@PreAuthorize("hasAuthority('users')")
	@GetMapping("/getAllLiaisonBankily")
	public  ResponseDto  getAllLiaisonBankily() throws Exception {
		   List<LiaisonBankily>	bankilies = iLiaisonBankilyService.getAllLiaisonBankily();
			return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), bankilies);
		}
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/getCompteByCif",method=RequestMethod.POST)
	public @ResponseBody ResponseDto getCompteByCif(@RequestBody LiaisonRequest req) throws Exception {
		
		ListLiaisonResponse res=iLiaisonBankilyService.getCompteByCif(req);
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res );

	}
	
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/getVerificationImalByCif/{cif}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getVerificationImalByCif(@PathVariable String cif) throws Exception {
		VerificationImalResponse dto=iLiaisonBankilyService.getVerificationImalByCif(cif);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);

	}
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/getVerificationMobileByTelephone/{phone}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getVerificationMobileByTelephone(@PathVariable String phone) throws Exception {
		VerificationImalResponse dto=iLiaisonBankilyService.getVerificationMobileByTelephone(phone);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);

	}
}
