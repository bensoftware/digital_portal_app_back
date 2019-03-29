package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long>{

	
	@Query("select u from Notification u where u.typeNotification=1 and u.status!=2")
	public List<Notification> getAllNotificationEpuisementUsed();
	
	
	@Query("select u from Notification u where u.typeNotification=2 and u.status!=2")
	public List<Notification> getAllNotificationExpirationUsed();
	
	@Query("select u from Notification u where u.status!=2")
	public List<Notification> getAllNotificationUsed();
	
	@Query("select u from Notification u where u.status=0")
	public List<Notification> getAllNewNotification();
	
	@Query("select u from Notification u where u.status!=2 and u.typeNotification=?1")
	public List<Notification> getAllNotificationUsedByType(int typeNotification);

}
