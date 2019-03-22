package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.Expediteur;
import com.monetique.entities.Expiration;

public interface ExpirationRepository extends CrudRepository<Expiration, Long>{

	@Query("select u from Expiration u order by u.dateCreation desc")
	List<Expiration> getAllExpiration();
}
