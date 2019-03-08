package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.Expediteur;

public interface ExpediteurRepository extends CrudRepository<Expediteur, Integer>{

	@Query("select u from Expediteur u")
	List<Expediteur> getAllExpediteur();

}
