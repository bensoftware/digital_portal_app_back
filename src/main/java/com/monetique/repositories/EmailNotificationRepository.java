package com.monetique.repositories;

import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.CarteUtilise;
import com.monetique.entities.EmailNotification;

public interface EmailNotificationRepository extends CrudRepository<EmailNotification, Long>{


}
