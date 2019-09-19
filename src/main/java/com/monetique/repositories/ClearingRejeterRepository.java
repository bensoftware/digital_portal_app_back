package com.monetique.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.ClearingRejeter;

public interface ClearingRejeterRepository extends CrudRepository<ClearingRejeter, Long>{

	
	@Query("select u from ClearingRejeter u where u.clearingFile.nom=?1")
	public List<ClearingRejeter> getClearingByfile(String filename);
	
	@Query("select u from ClearingRejeter u where u.dateDeProcessing=?1 and u.typeEnregistrement=?2")
	public List<ClearingRejeter> getClearingPerso2(Date dateProc,String typeEnreg);
	
	@Query("select u from ClearingRejeter u where u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<ClearingRejeter> getClearingByDateIntervall(Date du, Date au);
	
	@Query("select u from ClearingRejeter u where u.typeEnregistrement='10' and  u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<ClearingRejeter> getClearingByDateIntervallCreditCommercant(Date du, Date au);
	
	@Query("select u from ClearingRejeter u where u.typeEnregistrement='20' and u.typeEnregistrement='30'  and  u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<ClearingRejeter> getClearingByDateIntervallTran(Date du, Date au);
}
