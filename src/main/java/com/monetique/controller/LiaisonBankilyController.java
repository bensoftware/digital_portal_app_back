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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LiaisonBankilyController {
	@Autowired
	private ILiaisonBankilyService iLiaisonBankilyService;
	@Autowired
	HttpServletResponse  httpServletResponse;
    Logger logger = LoggerFactory.getLogger(LiaisonBankilyController.class);
	
	@PreAuthorize("hasAuthority('validlb')")
	@RequestMapping(value="/addLiaisonBankily",method= RequestMethod.POST)
	public LiaisonBankily addLiaisonBankily(@RequestBody LiaisonBankily liaisonBankily) throws Exception {
		logger.info("addLiaisonBankily [IN] : "+liaisonBankily);
		LiaisonBankily out=iLiaisonBankilyService.addLiaisonBankily(liaisonBankily);
		logger.info("addLiaisonBankily [OUT] : "+out);
		return out;
	}
	
	@PreAuthorize("hasAuthority('apprlb')")
	@RequestMapping(value="/addApprobation",method=RequestMethod.POST)
	public @ResponseBody ResponseDto addApprobation(@RequestBody Approbation r) throws Exception {
		logger.info("addApprobation [IN] : "+r);
		ResponseDto out= new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), iLiaisonBankilyService.addApprobation(r));
		logger.info("addApprobation [IN] : "+out);
		return out;
	}
	@PreAuthorize("hasAuthority('apprlb')")
	@RequestMapping(value="/rejetApprobation",method=RequestMethod.POST)
	public @ResponseBody ResponseDto rejetApprobation(@RequestBody Approbation r) throws Exception {

		logger.info("rejetApprobation [IN] : "+r);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), iLiaisonBankilyService.addRejet(r));
		logger.info("addApprobation [OUT] : "+out);
		return out;
	}
	

	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@GetMapping("/getAllLiaisonBankily")
	public  ResponseDto  getAllLiaisonBankily() throws Exception {
		logger.info("getAllLiaisonBankily [IN] : ");
		   List<LiaisonBankily>	bankilies = iLiaisonBankilyService.getAllLiaisonBankily();
		   ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), bankilies);
			logger.info("getAllLiaisonBankily [OUT] : "+out);
			return out;
		}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getCompteByCif",method=RequestMethod.POST)
	public @ResponseBody ResponseDto getCompteByCif(@RequestBody LiaisonRequest req) {
		logger.info("getCompteByCif [IN] : ");
		ListLiaisonResponse res=null;
		res=iLiaisonBankilyService.getCompteByCif(req);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),res );
		logger.info("getCompteByCif [OUT] : "+out);
		return out;
	}
	
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getVerificationImalByCif/{cif}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getVerificationImalByCif(@PathVariable String cif) throws Exception {

		logger.info("getVerificationImalByCif [IN] : ");
		VerificationImalResponse dto=iLiaisonBankilyService.getVerificationImalByCif(cif);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
		logger.info("getVerificationImalByCif [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getVerificationMobileByTelephone/{phone}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getVerificationMobileByTelephone(@PathVariable String phone) throws Exception {
		logger.info("getVerificationMobileByTelephone [IN] : ");
		VerificationImalResponse dto=iLiaisonBankilyService.getVerificationMobileByTelephone(phone);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
		logger.info("getVerificationMobileByTelephone [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/updateLiaisonBankily")
	public LiaisonBankily updateLiaisonBankily(@RequestBody LiaisonBankily liaisonBankily) throws Exception {
		logger.info("updateLiaisonBankily [IN] : ");
		LiaisonBankily out= iLiaisonBankilyService.updateLiaisonBankily(liaisonBankily);
		logger.info("updateLiaisonBankily [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@GetMapping("/getAllLiaisonBankilyByIdGroup/{idG}")
	public  ResponseDto  getAllLiaisonBankilyByIdGroup(@PathVariable long idG) throws Exception {
		logger.info("getAllLiaisonBankilyByIdGroup [IN] : ");
		   List<LiaisonBankily>	bankilies = iLiaisonBankilyService.getAllLiaisonBankilyByIdGroup(idG);
		   ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), bankilies);
			logger.info("getAllLiaisonBankilyByIdGroup [OUT] : "+out);
			return out;
			}

	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/addLiaison",method=RequestMethod.POST)
	public @ResponseBody ResponseDto addLiaison(@RequestBody Liaison r) throws Exception {
		logger.info("addLiaison [IN] : ");
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), iLiaisonBankilyService.addLiaison(r));
		logger.info("addLiaison [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getGroupeByUsername/{username}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getGroupeByUsername(@PathVariable String username) throws Exception {
		logger.info("getGroupeByUsername [IN] : ");
		long dto=iLiaisonBankilyService.getGroupeByUsername(username);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
		logger.info("getGroupeByUsername [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getUserIdByLogin/{username}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getUserIdByLogin(@PathVariable String username) throws Exception {
		logger.info("getUserIdByLogin [IN] : ");
		VerificationImalResponse dto=iLiaisonBankilyService.getUserIdByLogin(username.toUpperCase());
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
		logger.info("getUserIdByLogin [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value="/getUserIdByTelephone/{phone}",method=RequestMethod.GET)
	public @ResponseBody ResponseDto getUserIdByTelephone(@PathVariable String phone) throws Exception {
		logger.info("getUserIdByTelephone [IN] : ");
		VerificationImalResponse dto=iLiaisonBankilyService.getUserIdByTelephone(phone);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
		logger.info("getUserIdByTelephone [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/updateRejetLiaisonBankily")
	public LiaisonBankily updateRejetLiaisonBankily(@RequestBody LiaisonBankily liaisonBankily) throws Exception {
		logger.info("updateRejetLiaisonBankily [IN] : ");
		LiaisonBankily out= iLiaisonBankilyService.updateRejetLiaisonBankily(liaisonBankily);
		logger.info("updateRejetLiaisonBankily [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@GetMapping("/getLiaisonBankilyByTelephone/{telephone}")
	public LiaisonBankily getLiaisonBankilyByTelephone(@PathVariable String telephone) {
		logger.info("getLiaisonBankilyByTelephone [IN] : ");
		LiaisonBankily out= iLiaisonBankilyService.getLiaisonBankilyByTelephone(telephone);
		logger.info("getLiaisonBankilyByTelephone [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/addExceptionMessage")
	public ExceptionMessage addExceptionMessage(@RequestBody ExceptionMessage exceptionMessage) throws Exception {
		logger.info("addExceptionMessage [IN] : ");
		ExceptionMessage out= iLiaisonBankilyService.addExceptionMessage(exceptionMessage);
		logger.info("addExceptionMessage [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/addAlert")
	public Alert addAlert(@RequestBody Alert alert) throws Exception {
		logger.info("addAlert [IN] : ");
		Alert out= iLiaisonBankilyService.addAlert(alert);
		logger.info("addAlert [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/getVerificationMobile")
	public @ResponseBody ResponseDto getVerificationMobile(@RequestBody VerificationMobileRequest mobileRequest) throws Exception {
		logger.info("getVerificationMobile [IN] : ");
		System.err.println(mobileRequest);
		VerificationMobileResponse dto=iLiaisonBankilyService.getVerificationMobile(mobileRequest);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), dto);
		logger.info("getVerificationMobile [OUT] : "+out);
		return out;
	}
	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@PostMapping("/deleteLiaisonIncompleteMobile")
	public @ResponseBody ResponseDto deleteLiaisonIncompleteMobile(@RequestBody LiaisonIncomplet incomplet) throws Exception {
		logger.info("deleteLiaisonIncompleteMobile [IN] : ");
		iLiaisonBankilyService.deleteLiaisonIncompleteMobile(incomplet);
		ResponseDto out=new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
		logger.info("deleteLiaisonIncompleteMobile [OUT] : "+out);
		return out;
	}

	@PreAuthorize ("hasAnyAuthority ('apprlb', 'validlb')")
	@RequestMapping(value = "/uploadFilePdf/{id}" , method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request,@PathVariable long id) throws Exception {
		logger.info("uploadFile [IN] : ");
		iLiaisonBankilyService.uploadFile(request,id);
		logger.info("uploadFile [OUT] : ");
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
