package com.kuebiko.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuebiko.dao.entity.CreditCardDetailEntity;

@Repository
public interface CreditCardDetailRepository extends JpaRepository<CreditCardDetailEntity, Integer> {
	
	 List<CreditCardDetailEntity> findByEmail(String email);
	 Optional<CreditCardDetailEntity> findByApplicationId(String applicationId);

}
