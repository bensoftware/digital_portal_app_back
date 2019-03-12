package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long>{

	
	@Query("select u from Notification u where u.typeNotification=1 and u.status=1")
	public List<Notification> getAllNotificationUsed();


}
