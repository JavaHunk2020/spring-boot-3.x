package com.kuebiko.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuebiko.dao.entity.CreditCardApplicationEntity;

@Repository
public interface CreditCardApplicationRepository extends JpaRepository<CreditCardApplicationEntity, Integer> {
	
	 List<CreditCardApplicationEntity> findByEmail(String email);
	 List<CreditCardApplicationEntity> findBySid(int sid);
	 CreditCardApplicationEntity findByApplicationId(String applicationId);
	 boolean existsByEmail(String email);
	 

}
