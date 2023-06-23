package com.kuebiko.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kuebiko.dao.entity.LoginHistoryEntity;

public interface LoginHistoryRepository  extends JpaRepository<LoginHistoryEntity, Integer>{
	
	 @Query(value = "from LoginHistoryEntity lh where lh.signupEntity.sid =?1")
	public List<LoginHistoryEntity> findAllBySignupId(int sid);

}
