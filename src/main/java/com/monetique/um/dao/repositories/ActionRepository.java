package com.monetique.um.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monetique.um.dao.entities.Action;

/**
 * Interface DAO des méthodes CRUD des actions.
 *
 * @author  bpm digital
 *
 */
@Repository
public interface ActionRepository extends JpaRepository<Action, Long>{
	Action findByLibelle(String libelle);
}
