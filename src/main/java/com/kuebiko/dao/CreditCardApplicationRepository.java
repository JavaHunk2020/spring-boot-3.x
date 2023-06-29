package com.kuebiko.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuebiko.dao.entity.CreditCardApplicationEntity;

@Repository
public interface CreditCardApplicationRepository extends JpaRepository<CreditCardApplicationEntity, Integer> {

}
