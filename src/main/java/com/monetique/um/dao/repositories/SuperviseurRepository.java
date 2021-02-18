package com.monetique.um.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.monetique.um.dao.entities.Superviseur;



public interface SuperviseurRepository extends CrudRepository<Superviseur, Long>{

	List <Superviseur> findByActiveTrue();

}
