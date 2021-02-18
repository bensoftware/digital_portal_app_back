package com.monetique.um.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monetique.um.dao.entities.DeblocageLog;
import com.monetique.um.dao.entities.OtpLog;

public interface DeblocageLogRepository extends JpaRepository<DeblocageLog, Long>{

}