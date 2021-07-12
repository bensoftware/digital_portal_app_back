package com.monetique.um.service;

import java.util.List;
import com.monetique.um.dao.entities.Groupe;
public interface IGroupeService {
	public Groupe addGroupe(Groupe groupe);
	public Groupe updateGroupe(Groupe groupe);
	public Groupe getGroupe(long id);
	public List<Groupe> getAllGroupe();
	public List<Groupe> getAllGroupeActive();
	public int changerEtatGroup(long id,boolean active);
}
