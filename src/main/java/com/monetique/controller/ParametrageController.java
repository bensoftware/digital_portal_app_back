package com.monetique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.entities.Parametrage;
import com.monetique.service.ParametreService;

@CrossOrigin("*")
@RestController
public class ParametrageController {
	

	
	@Autowired
	ParametreService parametreService ;
	
	@RequestMapping(value="/getParameter",method=RequestMethod.GET)
	public @ResponseBody Parametrage getParameter() throws Exception {
		return  parametreService.getParameter();
	}
	@RequestMapping(value="/setParameter",method=RequestMethod.POST)
	public @ResponseBody Parametrage setParameter(@RequestBody Parametrage p) throws Exception {
		return  parametreService.setParameter(p);
	}
	
	
	
}
