package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.EmailNotification;

public interface EmailNotificationRepository extends CrudRepository<EmailNotification, Long>{

	@Query("select u.email from EmailNotification u where u.valide=true")
	public List<String> getEmailActif();
}
