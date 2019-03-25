package com.monetique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.dto.ItemTypeRecharge;
import com.monetique.entities.Parametrage;
import com.monetique.entities.TypeMontant;
import com.monetique.service.MontantService;
import com.monetique.service.ParametreService;

@CrossOrigin("*")
@RestController
public class ParametrageController {
	

	
	@Autowired
	ParametreService parametreService ;
	
	@Autowired
	MontantService  montantService ;
	
	@RequestMapping(value="/getParameter",method=RequestMethod.GET)
	public @ResponseBody Parametrage getParameter() throws Exception {
		return  parametreService.getParameter();
	}
	@RequestMapping(value="/setParameter",method=RequestMethod.POST)
	public @ResponseBody Parametrage setParameter(@RequestBody Parametrage p) throws Exception {
		return  parametreService.setParameter(p);
	}
	
	
	@RequestMapping(value="/getAllRecharge",method=RequestMethod.GET)
	public @ResponseBody List<ItemTypeRecharge> getAllRecharge() throws Exception {
		return  montantService.getAllRecharge();
	}
	
	@RequestMapping(value="/getTypeRecharge/{id}",method=RequestMethod.GET)
	public @ResponseBody TypeMontant getTypeRecharge(@PathVariable long id) throws Exception {
		return  montantService.getTypeRecharge(id);
	}
	
	@RequestMapping(value="/setTypeRecharge",method=RequestMethod.POST)
	public @ResponseBody TypeMontant setTypeRecharge(@RequestBody ItemTypeRecharge c) throws Exception {
		return  montantService.setTypeRecharge(c);
	}
	
	
}
