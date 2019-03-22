package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.CarteItem;
import com.monetique.entities.Expediteur;

public interface CarteItemRepository extends CrudRepository<CarteItem, String>{

	@Query("select u from CarteItem u where u.expiration.id=?1")
	public List<CarteItem> getAllCarteByExp(long id);

}
