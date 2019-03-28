package com.monetique.um.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monetique.um.dao.entities.Action;
import com.monetique.um.dao.repositories.ActionRepository;
import com.monetique.um.service.IActionService;

/**
 * Implémentation service action.
 * 
 * @author bpm digital
 *
 */
@Service
public class ActionServiceImpl implements IActionService{

	@Autowired
	private ActionRepository actionRepository;

	@Override
	public boolean createAction(Action action) throws Exception {
		if (action == null)
			throw new Exception("L'action à sauvegarder est nulle");
		
		// On vérifie la présence de libellé
		if (action.getLibelle() == null || (action.getLibelle() != null && action.getLibelle().equals("")))
			throw new Exception("Le libellé de l'action est à renseigner");

		if (action.getId() == null && actionRepository.findByLibelle(action.getLibelle().toUpperCase()) != null)
			throw new Exception("Ce libelle d'Action existe déjà!");

		//ajout action 
		Action act = new Action();
		if(action.getId()!=null) 
		act.setId(action.getId());
		act.setLibelle(action.getLibelle());
		act = actionRepository.saveAndFlush(act);
		/*if (app.getId() == null) {
			journalService.logJournal(this.getClass().getMethods(), "createAction", "Création de l'Action " + app.getCode(), "Action", appToSave.getId());
		}
		else {
			journalService.logJournal(this.getClass().getMethods(), "createAction", "Modification de l'Action " + app.getCode(), "Action", appToSave.getId());
		}*/
		return true;
	}

	@Override
	public List<Action> getListeAction() throws Exception {
		List<Action> actions = actionRepository.findAll();
		return actions;
	}

	@Override
	public boolean deleteAction(Long id) throws Exception {
		if (id == null)  throw new Exception("id action est nul");
		Optional<Action> optAction = actionRepository.findById(id);
		if (!optAction.isPresent())
			throw new Exception("Aucune action retrouvée avec cet identifiant");
		actionRepository.delete(optAction.get());
		return true;
	}

	@Override
	public Action findAction(Long id) throws Exception {
		if (id == null)
			throw new Exception("L'ID de recherche d'une action est nul");
		
		 Optional<Action> optAction = actionRepository.findById(id);
		
		if (!optAction.isPresent())
			throw new Exception("Aucune action retrouvée avec cet identifiant");

		return optAction.get();
	}

}
