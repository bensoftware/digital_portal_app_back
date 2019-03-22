package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.MontantNotification;

public interface MontantNotificationRepository extends CrudRepository<MontantNotification, Long>{

	@Query("select u from MontantNotification u where u.notification.id=?1")
	List<MontantNotification> getMontantNotificationByNotifId(long id);

}
