package com.kuebiko.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuebiko.dao.entity.LoginHistoryEntity;

public interface LoginHistoryRepository  extends JpaRepository<LoginHistoryEntity, Integer>{

}
