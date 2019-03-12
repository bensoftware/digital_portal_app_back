package com.monetique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.monetique.entities.CarteUtilise;

public interface CarteUtiliseRepository extends CrudRepository<CarteUtilise, String>{

	
	@Query("select count(u) from CarteUtilise u where u.typeMontant.montant=?1 and u.expediteur.code=?2")
	public double getTotalRechUtiliseByExp(double montant,int server);
	
	@Query("select u from CarteUtilise u where u.id LIKE CONCAT('%',:code,'%')")
	public List<CarteUtilise> getConsommeByCode(@Param("code") String code);
	
	@Query("select u from CarteUtilise u where u.cleNumeroSerie LIKE CONCAT('%',:code,'%')")
	public List<CarteUtilise> getConsommeByCleSerie(@Param("code") String code);
	
	@Query("select u from CarteUtilise u where u.numeroCarte LIKE CONCAT('%',:code,'%')")
	public List<CarteUtilise> getConsommeByNumeroCarte(@Param("code") String code);
	
	@Query("select u from CarteUtilise u where u.telephonePorteur LIKE CONCAT('%',:code,'%')")
	public List<CarteUtilise> getConsommeByTelephonePorteur(@Param("code") String code);

	@Query("select u from CarteUtilise u where u.telephoneBeneficiaire LIKE CONCAT('%',:code,'%')")
	public List<CarteUtilise> getConsommeByTelephoneBeneficiaire(@Param("code") String code);
}
