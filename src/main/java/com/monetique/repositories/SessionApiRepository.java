package com.monetique.repositories;

import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.SessionApi;

public interface SessionApiRepository extends CrudRepository<SessionApi, String>{


	public SessionApi findByUsernameAndPassword(String username,String password);
	
	public SessionApi findByUsername(String username);
	
}
