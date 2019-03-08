package com.monetique.repositories;

import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.CarteUtilise;
import com.monetique.entities.IntegrationException;

public interface IntegrationExceptionRepository extends CrudRepository<IntegrationException, Long>{


}
