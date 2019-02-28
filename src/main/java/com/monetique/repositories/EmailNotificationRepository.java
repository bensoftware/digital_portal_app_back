package com.monetique.repositories;

import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.CarteUtilise;

public interface EmailNotificationRepository extends CrudRepository<CarteUtilise, String>{


}
