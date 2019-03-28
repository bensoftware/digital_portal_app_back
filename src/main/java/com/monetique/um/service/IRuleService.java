package com.monetique.um.service;

import java.util.List;

import com.monetique.um.dao.entities.Action;
import com.monetique.um.dao.entities.Rule;

public interface IRuleService {

	public void addRule(Rule rule) throws Exception;
	public void updateRule(Rule rule) throws Exception;
	public List<Rule> getAllRule();
	public List<Rule> getAllRuleActive();
	public Rule getRule(long id) throws Exception;
	public void deleteRule(Long id) throws Exception;
	public void changeEtatRule(Long id) throws Exception;
	public void addActionsToRule(List<Action> actions,long idRule) throws Exception;
	public void removeActionsToRule(List<Action> actions,long idRule) throws Exception;
	public void addActionToRule(Action action,long idRule) throws Exception;
	public void removeActionToRule(Action action,long idRule) throws Exception;
	public void changeStatusRule(Long id) throws Exception;
	
}
