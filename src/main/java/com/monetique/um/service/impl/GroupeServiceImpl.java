package com.monetique.um.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dao.repositories.GroupeRepository;
import com.monetique.um.service.IGroupeService;
@Service
@Transactional
public class GroupeServiceImpl implements IGroupeService
{
	@Autowired
	GroupeRepository groupeRepository;

	@Override
	public Groupe addGroupe(Groupe groupe) {
		return groupeRepository.save(groupe);
	}

	@Override
	public Groupe updateGroupe(Groupe groupe) {
		Groupe p=groupeRepository.findById(groupe.getId()).get();
		groupe.setId(p.getId());
		return groupeRepository.save(groupe);
	}

	@Override
	public Groupe getGroupe(long id) {
		return groupeRepository.findById(id).get();
	}

	@Override
	public List<Groupe> getAllGroupe() {
		return groupeRepository.findAll();
	}

	@Override
	public List<Groupe> getAllGroupeActive() {
		return groupeRepository.getAllGroupeActive();
	}

	@Override
	public int changerEtatGroup(long id, boolean active) {
		return groupeRepository.changerEtatGroup(id, active);
	}

}
