package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.IntegrationException;

public interface IntegrationExceptionRepository extends CrudRepository<IntegrationException, Long>{


	@Query("select u from IntegrationException u where u.integrationFile.id=?1")
	public List<IntegrationException> getAllIntegrationExcepByHist(long id);
	
	@Query("select u from IntegrationException u where u.integrationFile.operateur.code=?1")
	public List<IntegrationException> getAllIntegrationExcepByOperator(int operator);

	
}
