package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.TypeMontant;

public interface TypeMontantRepository extends CrudRepository<TypeMontant, Long>{

	@Query("select u from TypeMontant u where u.montant=?1 and u.operateur.code=?2")
	public TypeMontant getExistMontantByOperateur(double montant, int operator);
	
	@Query("select u.montant from TypeMontant u where u.operateur.code=?1")
	public List<Double> getAllMontantByOperateur(int operator);
	
	
}
