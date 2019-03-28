package com.monetique.um.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.um.dao.entities.Action;
import com.monetique.um.dao.entities.Rule;
import com.monetique.um.dao.repositories.ActionRepository;
import com.monetique.um.dao.repositories.RuleRepository;
import com.monetique.um.service.IRuleService;

@Service
public class RuleServiceImpl implements IRuleService{
	
	@Autowired
	RuleRepository ruleRepository;
	
	@Autowired
	ActionRepository actionRepository;

	@Override
	public void addRule(Rule rule) throws Exception {
		
		if(rule==null) {
			throw new Exception("le rôle est null");
		}
		else {
			if(rule.getLibelle()==null) {
				throw new Exception("le libelle du rôle est null");	
			}
		}
		
		ruleRepository.save(rule);
	}

	@Override
	public void updateRule(Rule rule) throws Exception {
		
		if(rule==null) {
			throw new Exception("le rôle est null");
		}
		else {
			if(rule.getLibelle()==null) {
				throw new Exception("le libelle du rôle est null");	
			}
		}
		
		ruleRepository.saveAndFlush(rule);
	}

	@Override
	public List<Rule> getAllRule() {
		return ruleRepository.findAll();
	}

	@Override
	public Rule getRule(long id) throws Exception {
		
		Optional<Rule> opt= ruleRepository.findById(id);
		
		if(!opt.isPresent())
			throw new Exception("ce rôle n'existe pas");	
			
		 return opt.get();
		
	}

	@Override
	public void deleteRule(Long id) throws Exception{
		if (id == null)  throw new Exception("id rôle est nul");
		ruleRepository.deleteById(id);
	}

	@Override
	public void addActionsToRule(List<Action> actions, long idRule) throws Exception{
		if(idRule==0 && actions==null ) throw new Exception("Id rôle ou liste des action non définit");
	   	Optional<Rule> ruleOp=ruleRepository.findById(idRule);
	   	
	   	if(ruleOp.isPresent()) {
	   		
	   		Rule rule=ruleOp.get();
	   		List<Action> listAction=rule.getActions();
	   		listAction.addAll(actions);
	   		
	   		ruleRepository.saveAndFlush(rule);
	   		
	   	}
			
	}

	@Override
	public void removeActionsToRule(List<Action> actions, long idRule) throws Exception {
		if(idRule==0 && actions==null ) throw new Exception("Id rôle ou liste des rules non définit");
		Optional<Rule> ruleOp=ruleRepository.findById(idRule);
		   	
		   	if(ruleOp.isPresent()) {
		   		
		   		Rule rule=ruleOp.get();
		   		
		   		List<Action> listAction= rule.getActions();
		   		
		   		
		   		// traitement de suppression

		   		actions.forEach(e->{
		   			for(Action a : listAction) {
		   				if(e.getId()==a.getId()) {
		   					listAction.remove(a);
		   					break;
		   				}
		   			}
		   		});
		   		
		   		ruleRepository.saveAndFlush(rule);
		   		
		   	}
	}

	@Override
	public void changeStatusRule(Long id) throws Exception{
	if (id == null) throw new Exception("Id du rôle non définit");	
   	Optional<Rule> ruleOp=ruleRepository.findById(id);
	 	if(ruleOp.isPresent()) {
	   		Rule rule=ruleOp.get();
	   		boolean active=false;
	   		if(!rule.isActive())
	   			active=true;
	   		
	   		rule.setActive(active);
	   		ruleRepository.saveAndFlush(rule);
	 }	
	}

	@Override
	public void addActionToRule(Action action, long idRule) throws Exception {
		if(idRule==0 && action==null ) throw new Exception("Id rôle ou  action non définit");
	   	Optional<Rule> ruleOp=ruleRepository.findById(idRule);
	   	
	   	if(ruleOp.isPresent()) {
	   		
	   		Rule rule=ruleOp.get();
	   		List<Action> listAction=rule.getActions();
	   		
	   		boolean existe=false;
	   		
	   		for(Action ac: listAction) {
	   			if(ac.equals(action)) {
	   				existe=true;
	   				break;
	   			}
	   		}
	   		
	   		if(!existe)
	   		listAction.add(action);
	   		
	   		ruleRepository.saveAndFlush(rule);
	   		
	   	}
		
	}

	@Override
	public void removeActionToRule(Action action, long idRule) throws Exception {

		if(idRule==0 && action==null ) throw new Exception("Id rôle ou rule non définit");
		Optional<Rule> ruleOp=ruleRepository.findById(idRule);
		   	
		   	if(ruleOp.isPresent()) {
		   		
		   		Rule rule=ruleOp.get();
		   		
		   		List<Action> listAction= rule.getActions();
		   		
		   		
		   		// traitement de suppression
/*
		   		for(Action ac : listAction) {
		   			if(ac.equals(action)) {
		   				
		   			}
		   		}*/
		   		boolean existe=false;
		   		Action supp=null;
		   		
		   		for(Action ac: listAction) {
		   			if(ac.getMotCle().equals(action.getMotCle()) && ac.getId().equals(action.getId())) {
		   				existe=true;
		   				supp=ac;
		   				break;
		   			}
		   		}
		   		
		   		if(existe)
		   		listAction.remove(supp);
		   		
		   		ruleRepository.saveAndFlush(rule);
		   		
		   	}
	}

	@Override
	public List<Rule> getAllRuleActive() {
		// TODO Auto-generated method stub
		return ruleRepository.getAllRuleActive();
	}

	@Override
	public void changeEtatRule(Long id) throws Exception {
		
		Optional<Rule>  op= ruleRepository.findById(id);
		
		if(op.isPresent()) {
			Rule rule=op.get();
			rule.setActive(!rule.isActive());
			ruleRepository.saveAndFlush(rule);
		}
		
	}

}
