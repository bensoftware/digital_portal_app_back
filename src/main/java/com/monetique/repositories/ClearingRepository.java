package com.monetique.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.entities.Clearing;

public interface ClearingRepository extends CrudRepository<Clearing, Long>{

	@Query("select u from Clearing u where u.clearingFile.nom=?1")
	public List<Clearing> getClearingByfile(String filename);
	
	@Query("select u from Clearing u where u.dateDeTransaction=?1 and u.typeEnregistrement=?2")
	public List<Clearing> getClearingPerso1(String dateTrans,String typeEnreg);
	
	
	@Query("select u from Clearing u where u.dateDeProcessing=?1 and u.typeEnregistrement=?2")
	public List<Clearing> getClearingPerso2(Date dateProc,String typeEnreg);
	
	@Query("select u from Clearing u where u.dateDeProcessing=?1")
	public List<Clearing> getClearingPerso3(Date dateProc);
	
	@Query("select u from Clearing u where u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<Clearing> getClearingByDateIntervall(Date du, Date au);
	
	@Query("select u from Clearing u where u.typeEnregistrement='10' and  u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<Clearing> getClearingByDateIntervallCreditCommercant(Date du, Date au);
	
	@Query("select u from Clearing u where u.typeEnregistrement='20' and u.typeEnregistrement='30'  and  u.dateDeProcessing >= ?1 and u.dateDeProcessing <= ?2")
	public List<Clearing> getClearingByDateIntervallTran(Date du, Date au);
}
