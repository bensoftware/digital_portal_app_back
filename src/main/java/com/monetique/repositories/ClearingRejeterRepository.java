package com.monetique.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.ClearingRejeter;

public interface ClearingRejeterRepository extends CrudRepository<ClearingRejeter, Long>{

	@Query("select u from ClearingRejeter u where u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<ClearingRejeter> getClearingByDateIntervall(Date du, Date au);
}
