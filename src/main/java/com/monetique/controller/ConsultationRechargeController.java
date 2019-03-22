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

import com.monetique.dto.CarteExport;
import com.monetique.dto.CarteItem;
import com.monetique.dto.ConsultationRecharge;
import com.monetique.service.CarteStockService;
import com.monetique.service.CarteUtiliserService;

@CrossOrigin("*")
@RestController
public class ConsultationRechargeController {
	
	@Autowired
	CarteStockService carteStockService ;
	
	@Autowired
	CarteUtiliserService carteUtiliserService ;
		
	
	
	
	@RequestMapping(value="/getConsultationRecharge/{type}",method=RequestMethod.GET)
	public @ResponseBody List<ConsultationRecharge> getConsultationRecharge(@PathVariable int type) throws Exception {
		
		List<ConsultationRecharge> res=null;
		
		if(type==1) {
			res=carteStockService.getConsultationRechargeStock();
		}
		else if(type==2) {
			res=carteUtiliserService.getConsultationRechargeUtilise();
		}
		
		return res;
	}
	
	
	@RequestMapping(value="/getRechercheRecharge",method=RequestMethod.POST)
	public @ResponseBody List<CarteItem> getConsultationRecharge(@RequestParam int type,@RequestParam String recherche) throws Exception {
		

		return carteUtiliserService.getRechercheRecharge(type, recherche);
	}
	
	@RequestMapping(value="/getAllExpiration",method=RequestMethod.GET)
	public @ResponseBody List<CarteExport> getAllExpiration() throws Exception {
	
		return carteStockService.getAllExpiration();
	}

}
