package com.kuebiko.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuebiko.dao.entity.GalleryEntity;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Integer> {
	  
	
}
