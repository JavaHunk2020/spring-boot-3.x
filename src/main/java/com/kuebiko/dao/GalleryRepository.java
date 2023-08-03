package com.kuebiko.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kuebiko.dao.entity.GalleryEntity;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Integer> {
	  
	@Query(value = "SELECT max(id) FROM GalleryEntity")
	public int getMaxId();
	
}
