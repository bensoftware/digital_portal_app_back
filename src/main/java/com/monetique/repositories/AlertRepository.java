package com.monetique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monetique.um.dao.entities.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{

}
