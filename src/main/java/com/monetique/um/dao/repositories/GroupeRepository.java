package com.monetique.um.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.monetique.um.dao.entities.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long>{
	@Query("select g from Groupe g where g.active=true")
	public List<Groupe> getAllGroupeActive();
	@Modifying
	@Query("update Groupe g set g.active=?2 where g.id=?1")
	public int changerEtatGroup(long id, boolean active);	
}
