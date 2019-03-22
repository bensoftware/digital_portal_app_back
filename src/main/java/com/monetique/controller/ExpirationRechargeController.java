package com.monetique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.CarteExport;
import com.monetique.dto.ExpirationItem;
import com.monetique.entities.Expiration;
import com.monetique.service.CarteStockService;
import com.monetique.service.ExpirationService;

@CrossOrigin("*")
@RestController
public class ExpirationRechargeController {
	
	@Autowired
	CarteStockService carteStockService ;
	
	@Autowired
	ExpirationService expirationService ;
		
	
	@RequestMapping(value="/getAllExport",method=RequestMethod.GET)
	public @ResponseBody List<Expiration> getAllExport() throws Exception {
	
		return expirationService.getAllExport();
	}
	
	@RequestMapping(value="/deleteExpiration",method=RequestMethod.POST)
	public @ResponseBody void deleteExpiration(@RequestParam long id) throws Exception {
	
		 expirationService.deleteExpiration(id);;
	}
	
	@RequestMapping(value="/getExport/{id}",method=RequestMethod.GET)
	public @ResponseBody  List<CarteExport>  getExport(@PathVariable long id) throws Exception {
	
		return expirationService.getExport(id);
	}
	
	@RequestMapping(value="/valideExport",method=RequestMethod.POST)
	public @ResponseBody  void valideExport(@RequestBody ExpirationItem item) throws Exception {
	
		 expirationService.valideExport(item.getIdExpiration(),item.getDateExpiration());
	}
	
	@RequestMapping(value="/saveExport",method=RequestMethod.POST)
	public @ResponseBody  void saveExport(@RequestBody ExpirationItem item) throws Exception {
		 expirationService.saveExport(item.getExpirations());
	}

}
