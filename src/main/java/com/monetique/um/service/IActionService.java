package com.monetique.um.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monetique.um.dao.entities.Action;

/**
 * Interface action.
 * 
 * @author bpm digital
 *
 */
@Service
public interface IActionService {

    public boolean createAction(Action action) throws Exception;
	
	public List<Action> getListeAction() throws Exception;

	public boolean deleteAction(Long id) throws Exception;
	 
	public Action findAction(Long id) throws Exception;
}
