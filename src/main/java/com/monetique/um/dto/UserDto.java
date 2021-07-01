package com.monetique.um.dto;

import java.io.Serializable;
import java.util.List;

import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dao.entities.Rule;


/**
 * Modèle Entité User Dto.
 *
 * @author bpm digital
 *
 */
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;

	 List<Rule> rules;
	 Rule rule;
	 List<Groupe> groupes;
	 Groupe groupe;
	 public List<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	long idUser;
	 
	 
	 
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	 
	 
}
