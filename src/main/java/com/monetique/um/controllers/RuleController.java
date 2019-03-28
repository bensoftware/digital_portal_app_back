package com.monetique.um.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.um.dao.entities.Rule;
import com.monetique.um.dto.ResponseDto;
import com.monetique.um.dto.RuleDto;
import com.monetique.um.service.IRuleService;

@RestController
@Transactional
//@CrossOrigin("*")
public class RuleController {
	
	@Autowired
	IRuleService ruleService;
	
	@Autowired
	HttpServletResponse  httpServletResponse;

	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/addRule",method= RequestMethod.POST)
	public @ResponseBody ResponseDto addRule(@RequestBody Rule rule) throws Exception{
		ruleService.addRule(rule);
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/updateRule",method= RequestMethod.POST)
	public @ResponseBody ResponseDto updateRule(@RequestBody Rule rule) throws Exception{
		ruleService.updateRule(rule);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);

	}
	
	
	
	@PreAuthorize("hasAnyAuthority('rules','users')")
	@RequestMapping(value="/getAllRuleActive",method= RequestMethod.GET)
	public @ResponseBody ResponseDto getAllRuleActive(){
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), ruleService.getAllRuleActive());
	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/getAllRule",method= RequestMethod.GET)
	public @ResponseBody ResponseDto getAllRule(){
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), ruleService.getAllRule());
	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/getRule",method= RequestMethod.GET)
	public @ResponseBody ResponseDto getRule(@RequestParam long id) throws Exception{
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), ruleService.getRule(id));

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/deleteRule",method= RequestMethod.DELETE)
	public @ResponseBody ResponseDto deleteRule(@RequestParam long id) throws Exception {
		ruleService.deleteRule(id);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/changeEtatRule",method= RequestMethod.POST)
	public @ResponseBody ResponseDto changeEtatRule(@RequestParam long id) throws Exception {
		ruleService.changeEtatRule(id);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/addActionsToRule",method= RequestMethod.POST)
	public @ResponseBody ResponseDto addActionsToRule(@RequestBody RuleDto ruleDto) throws Exception {
		ruleService.addActionsToRule(ruleDto.getActions(),ruleDto.getIdRule());
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/removeActionsToRule",method= RequestMethod.POST)
	public @ResponseBody ResponseDto removeActionsToRule(@RequestBody RuleDto ruleDto) throws Exception {
		ruleService.removeActionsToRule(ruleDto.getActions(),ruleDto.getIdRule());

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/addActionToRule",method= RequestMethod.POST)
	public @ResponseBody ResponseDto addActionToRule(@RequestBody RuleDto ruleDto) throws Exception {
		ruleService.addActionToRule(ruleDto.getAction(),ruleDto.getIdRule());
		
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/removeActionToRule",method= RequestMethod.POST)
	public @ResponseBody ResponseDto removeActionToRule(@RequestBody RuleDto ruleDto) throws Exception {
		ruleService.removeActionToRule(ruleDto.getAction(),ruleDto.getIdRule());

		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);

	}
	
	@PreAuthorize("hasAuthority('rules')")
	@RequestMapping(value="/changeStatusRule",method= RequestMethod.POST)
	public @ResponseBody void changeStatusRule(@RequestParam long id) throws Exception {
		ruleService.changeStatusRule(id);
	}
}
