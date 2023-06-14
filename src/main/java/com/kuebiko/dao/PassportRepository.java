package com.kuebiko.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuebiko.dao.entity.PassportEntity;

public interface PassportRepository extends JpaRepository<PassportEntity, Integer> {
}
