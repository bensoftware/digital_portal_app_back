package com.monetique.um.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monetique.um.dao.entities.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long>{

	@Query("SELECT u FROM Rule u WHERE u.active=true ")
    public List<Rule> getAllRuleActive();
	
	@Query("SELECT u FROM Rule u WHERE u.libelle=?1 ")
    public Rule findBylibelle(String lbl);
}
