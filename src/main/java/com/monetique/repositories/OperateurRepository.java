package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.Operateur;

public interface OperateurRepository extends CrudRepository<Operateur, Integer>{

	@Query("select u from Operateur u")
	List<Operateur> getAllOperator();


}
