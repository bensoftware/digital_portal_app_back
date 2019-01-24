package com.monetique.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.Clearing;
import com.monetique.entities.Reference;

public interface ClearingRepository extends CrudRepository<Clearing, Reference>{

	
	@Query("select u from Clearing u where u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<Clearing> getClearingByDateIntervall(Date du, Date au);
}
