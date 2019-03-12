package com.monetique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.IntegrationExcpItem;
import com.monetique.dto.ItemInfo;
import com.monetique.entities.IntegrationFile;
import com.monetique.entities.Operateur;
import com.monetique.service.IntegrationService;
import com.monetique.service.OperateurService;

@CrossOrigin("*")
@RestController
public class IntegrationController {
	
	@Autowired
	IntegrationService integrationService;
	
	@Autowired
	OperateurService operateurService;
		
	
	@RequestMapping(value="/getIntegrationVoucher",method=RequestMethod.POST)
	public @ResponseBody int getIntegrationVoucher(@RequestParam int operator,@RequestParam String filename) throws Exception {
		return  integrationService.integrationVoucher(operator, filename);
	}
	
	@RequestMapping(value="/getInfoVoucher/{operator}/{filename}",method=RequestMethod.GET)
	public @ResponseBody List<ItemInfo> getInfoVoucher(@PathVariable int operator,@PathVariable String filename) throws Exception {
		return  integrationService.getInfoVoucher(operator, filename);
	}
	
	@RequestMapping(value="/getOperator/{operator}",method=RequestMethod.GET)
	public @ResponseBody Operateur getOperator(@PathVariable int operator) throws Exception {
		return  operateurService.getOperatorByCode(operator);
	}
	
	@RequestMapping(value="/getListVoucher/{operator}",method=RequestMethod.GET)
	public @ResponseBody List<String> getListVoucher(@PathVariable int operator) throws Exception {
		return  integrationService.getListVouchers(operator);
	}
	
	@RequestMapping(value="/getHistoriqueVouchers/{operator}",method=RequestMethod.GET)
	public @ResponseBody List<IntegrationFile> getHistoriqueVouchers(@PathVariable int operator) throws Exception {
		return  integrationService.getHistoriqueIntegration(operator);
	}
	
	@RequestMapping(value="/getHistoriqueExceptionVouchers/{operator}",method=RequestMethod.GET)
	public @ResponseBody List<IntegrationFile> getHistoriqueExceptionVouchers(@PathVariable int operator) throws Exception {
		return  integrationService.getHistoriqueIntegrationException(operator);
	}

	@RequestMapping(value="/getExceptionVouchersByHisp/{id}",method=RequestMethod.GET)
	public @ResponseBody List<IntegrationExcpItem> getExceptionVouchersByHisp(@PathVariable long id) throws Exception {
		return  integrationService.getExceptionIntegByHisto(id);
	}
	
	
	@RequestMapping(value="/getExceptionByOp/{operator}",method=RequestMethod.GET)
	public @ResponseBody List<IntegrationExcpItem> getExceptionByOp(@PathVariable int operator) throws Exception {
		return  integrationService.getExceptionByOp(operator);
	}
}
