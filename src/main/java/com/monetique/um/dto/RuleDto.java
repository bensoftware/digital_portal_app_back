package com.monetique.um.dto;

import java.io.Serializable;
import java.util.List;

import com.monetique.um.dao.entities.Action;


/**
 * Modèle Entité Rule Dto.
 *
 * @author bpm digital
 *
 */
public class RuleDto implements Serializable{

	 private static final long serialVersionUID = 1L;

	 List<Action> actions;
	 long idRule;
	 Action action;
	 
	 
	 
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	public long getIdRule() {
		return idRule;
	}
	public void setIdRule(long idRule) {
		this.idRule = idRule;
	}
	 
	 
	 
}
