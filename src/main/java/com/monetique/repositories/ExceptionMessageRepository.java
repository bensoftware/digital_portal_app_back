package com.monetique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monetique.um.dao.entities.ExceptionMessage;

public interface ExceptionMessageRepository extends JpaRepository<ExceptionMessage, Long>{

}
