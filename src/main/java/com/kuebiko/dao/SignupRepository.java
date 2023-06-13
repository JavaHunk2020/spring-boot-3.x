package com.kuebiko.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuebiko.dao.entity.SignupEntity;

public interface SignupRepository extends JpaRepository<SignupEntity, Integer> {
	//find - By  -  attribute
	public Optional<SignupEntity> findByName(String username);
	public Optional<SignupEntity>  findByEmail(String email);
	public Optional<SignupEntity>  findByNameAndEmail(String username,String email);
}
