package com.monetique.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.CarteStock;
import com.monetique.entities.TypeMontant;

public interface CarteStockRepository extends CrudRepository<CarteStock, String>{

/*	@Query("select u from CarteStock u where u.typeMontant.montant=?1 and u.typeMontant.operateur.code=?2 limit(1)")
	CarteStock getRechargeStock(double montant,int operator);*/
	
	CarteStock findFirstByTypeMontant(TypeMontant typeMontant);

	CarteStock findFirstByTypeMontantOrderByDateExpirationAsc(TypeMontant typeMontant);

	CarteStock findFirstByTypeMontantOrderByDateExpiration(TypeMontant typeMontant);

	
	@Query("select count(u) from CarteStock u where u.typeMontant.id=?1")
	public double getTotalRecharge(long id);
	
	@Query("select u from CarteStock u where u.dateExpiration<?1")
	public List<CarteStock> getAllStockExpiration(Date exp);
	
}
