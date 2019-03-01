package com.monetique.repositories;

import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.EmailNotification;

public interface CarteUtiliseRepository extends CrudRepository<EmailNotification, Long>{


}
