package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.IntegrationFile;


public interface IntegrationFileRepository extends CrudRepository<IntegrationFile, Long>{

	@Query("select u from IntegrationFile u where u.filename=?1")
	public IntegrationFile getIntegrationFileByFilename(String filename);
	
	@Query("select u from IntegrationFile u where u.operateur.code=?1 order by u.date desc")
	public List<IntegrationFile> getAllIntegrationFileByOperator(int operator);
}
