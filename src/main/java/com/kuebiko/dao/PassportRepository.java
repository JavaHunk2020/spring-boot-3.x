package com.kuebiko.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kuebiko.dao.entity.PassportEntity;

public interface PassportRepository extends JpaRepository<PassportEntity, Integer> {
	  
	//HQL - in we write query 
	 @Query(value = "from PassportEntity as pe where pe.signupEntity.sid =?1")
	  public Optional<PassportEntity> findBySignupEntityId(int sid);
	
}
