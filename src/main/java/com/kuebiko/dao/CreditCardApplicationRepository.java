package com.kuebiko.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuebiko.dao.entity.CreditCardApplicationEntity;

@Repository
public interface CreditCardApplicationRepository extends JpaRepository<CreditCardApplicationEntity, Integer> {
	
	 Optional<CreditCardApplicationEntity> findByEmail(String email);
	 List<CreditCardApplicationEntity> findBySid(int sid);
	 boolean existsByEmail(String email);
	 

}
