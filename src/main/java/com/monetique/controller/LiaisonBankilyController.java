package com.monetique.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.dto.Approbation;
import com.monetique.dto.Liaison;
import com.monetique.dto.LiaisonIncomplet;
import com.monetique.dto.LiaisonRequest;
import com.monetique.dto.ListLiaisonResponse;
import com.monetique.dto.VerificationMobileRequest;
import com.monetique.dto.VerificationMobileResponse;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.service.ILiaisonBankilyService;
import com.monetique.um.dao.entities.Alert;
import com.monetique.um.dao.entities.ExceptionMessage;
import com.monetique.um.dao.entities.LiaisonBankily;
import com.monetique.um.dto.ResponseDto;
import com.monetique.um.dto.VerificationImalResponse;

@RestController
public class LiaisonBankilyController {
	@Autowired
	private ILiaisonBankilyService iLiaisonBankilyService;
	@Autowired
	HttpServletResponse  httpServletResponse;
	@PreAuthorize("hasAuthority('validlb')")
	@RequestMapping(value="/addLiaisonBankily",method= RequestMethod.POST)
	public LiaisonBankily addLiaisonBankily(@RequestBody LiaisonBankily liaisonBankily) throws Exception {
		return iLiaisonBankilyService.addLiaisonBankily(liaisonBankily);
	}
	
	@PreAuthorize("hasAuthority('apprlb')")
	@RequestMapping(value="/addApprobation",method=RequestMethod.POST)
	public @ResponseBody ResponseDto addApprobation(@RequestBody Approbation r) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), iLiaisonBankilyService.addApprobation(r));
	}
	@PreAuthorize("hasAuthority('apprlb')")
	@RequestMapping(value="/rejetApprobation",method=RequestMethod.POST)
	public @ResponseBody ResponseDto rejetApprobation(@RequestBody Approbation r) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), iLiaisonBankilyService.addRejet(r));
	}
	

	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@GetMapping("/getAllLiaisonBankily")
	public  ResponseDto  getAllLiaisonBankily() throws Exception {
		   List<LiaisonBankily>	bankilies = iLiaisonBankilyService.getAllLiaisonBankily();
			return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), bankilies);
		}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getCompteByCif",method=RequestMethod.POST)
	public @ResponseBody ResponseDto getCompteByCif(@RequestBody LiaisonRequest req) {
		ListLiaisonResponse res=null;
	
			res=iLiaisonBankilyService.getCompteByCif(req);

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res );
	}
	
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getVerificationImalByCif/{cif}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getVerificationImalByCif(@PathVariable String cif) throws Exception {
		VerificationImalResponse dto=iLiaisonBankilyService.getVerificationImalByCif(cif);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getVerificationMobileByTelephone/{phone}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getVerificationMobileByTelephone(@PathVariable String phone) throws Exception {
		VerificationImalResponse dto=iLiaisonBankilyService.getVerificationMobileByTelephone(phone);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/updateLiaisonBankily")
	public LiaisonBankily updateLiaisonBankily(@RequestBody LiaisonBankily liaisonBankily) throws Exception {
		return iLiaisonBankilyService.updateLiaisonBankily(liaisonBankily);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@GetMapping("/getAllLiaisonBankilyByIdGroup/{idG}")
	public  ResponseDto  getAllLiaisonBankilyByIdGroup(@PathVariable long idG) throws Exception {
		   List<LiaisonBankily>	bankilies = iLiaisonBankilyService.getAllLiaisonBankilyByIdGroup(idG);
			return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), bankilies);
			}

	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/addLiaison",method=RequestMethod.POST)
	public @ResponseBody ResponseDto addLiaison(@RequestBody Liaison r) throws Exception {
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), iLiaisonBankilyService.addLiaison(r));
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getGroupeByUsername/{username}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getGroupeByUsername(@PathVariable String username) throws Exception {
		long dto=iLiaisonBankilyService.getGroupeByUsername(username);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getUserIdByLogin/{username}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getUserIdByLogin(@PathVariable String username) throws Exception {
		VerificationImalResponse dto=iLiaisonBankilyService.getUserIdByLogin(username.toUpperCase());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getUserIdByTelephone/{phone}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getUserIdByTelephone(@PathVariable String phone) throws Exception {
		VerificationImalResponse dto=iLiaisonBankilyService.getUserIdByTelephone(phone);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/updateRejetLiaisonBankily")
	public LiaisonBankily updateRejetLiaisonBankily(@RequestBody LiaisonBankily liaisonBankily) throws Exception {
		return iLiaisonBankilyService.updateRejetLiaisonBankily(liaisonBankily);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@GetMapping("/getLiaisonBankilyByTelephone/{telephone}")
	public LiaisonBankily getLiaisonBankilyByTelephone(@PathVariable String telephone) {
		return iLiaisonBankilyService.getLiaisonBankilyByTelephone(telephone);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/addExceptionMessage")
	public ExceptionMessage addExceptionMessage(@RequestBody ExceptionMessage exceptionMessage) throws Exception {
		return iLiaisonBankilyService.addExceptionMessage(exceptionMessage);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/addAlert")
	public Alert addAlert(@RequestBody Alert alert) throws Exception {
		return iLiaisonBankilyService.addAlert(alert);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/getVerificationMobile")
	public @ResponseBody ResponseDto getVerificationMobile(@RequestBody VerificationMobileRequest mobileRequest) throws Exception {
		System.err.println(mobileRequest);
		VerificationMobileResponse dto=iLiaisonBankilyService.getVerificationMobile(mobileRequest);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/deleteLiaisonIncompleteMobile")
	public @ResponseBody ResponseDto deleteLiaisonIncompleteMobile(@RequestBody LiaisonIncomplet incomplet) throws Exception {
		iLiaisonBankilyService.deleteLiaisonIncompleteMobile(incomplet);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
	}

	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value = "/uploadFilePdf/{id}" , method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request,@PathVariable long id) throws Exception {
		iLiaisonBankilyService.uploadFile(request,id);
	}
	
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value = "/uploadFilePdfAutomatique/{cif}/{nni}/{telephone}" , method = RequestMethod.POST)
	public @ResponseBody com.monetique.dto.ResponseDto uploadFilePdfAutomatique(HttpServletRequest request,@PathVariable String cif,@PathVariable String nni,@PathVariable String telephone) throws Exception {
		return iLiaisonBankilyService.uploadFileAutomatique(request, cif, nni, telephone);
	}

    @GetMapping("/generationLiaisonPdf/{fileName}")
	public void generationPdf(HttpServletResponse resonse,@PathVariable String fileName) throws IOException {
		iLiaisonBankilyService.generationPdf(resonse, fileName);
	}

    @GetMapping("/batchLiaisonBankily")
	public void generateAllLiaisonQuotidient() throws Exception {
		iLiaisonBankilyService.generateAllLiaisonQuotidient();
	}

}
