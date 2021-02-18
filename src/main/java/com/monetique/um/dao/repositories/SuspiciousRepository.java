package com.monetique.um.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monetique.um.dao.entities.Suspicious;

public interface SuspiciousRepository extends JpaRepository<Suspicious, Long> {
	
	List <Suspicious> findByPhoneAndActiveTrue(String phone);
	List <Suspicious> findByNniAndActiveTrue(String nni);
	List <Suspicious> findByCifAndActiveTrue(String cif);


}
