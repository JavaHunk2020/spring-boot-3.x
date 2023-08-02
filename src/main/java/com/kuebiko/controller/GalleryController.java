package com.kuebiko.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kuebiko.dao.GalleryRepository;
import com.kuebiko.dao.entity.GalleryEntity;

@Controller
@RequestMapping("/action")
public class GalleryController {

	@Autowired
	private GalleryRepository galleryRepository;

	@PostMapping("/gallery")
	public String postGallery(MultipartFile photo, int gid) throws IOException {
		GalleryEntity galleryEntity = new GalleryEntity();
		galleryEntity.setId(gid);
		galleryEntity.setDoe(new Timestamp(new Date().getTime()));
		galleryEntity.setName(photo.getOriginalFilename());
		galleryEntity.setSize(photo.getSize());
		galleryEntity.setPhoto(photo.getBytes());
		galleryRepository.save(galleryEntity);
		return "gallery";
	}

	@GetMapping("/gimage")
	public void loadImage(@RequestParam int iid, HttpServletResponse response) throws IOException {
		// Fetch photo
		Optional<GalleryEntity> optional = galleryRepository.findById(iid);
		byte[] photo = {};
		if (optional.isPresent()) {
			photo = optional.get().getPhoto();
		}else {
			//READING IMAGE FROM FILE WHNE IMAGE DOES NOT EXIST INSIDE DATABASE
			Resource resource = new ClassPathResource("images/Eclat-Credit-Card.png");
			InputStream defaultPhoto = resource.getInputStream();
			photo=defaultPhoto.readAllBytes();
		}		
		response.setContentType("image/png");
		ServletOutputStream outputStream = response.getOutputStream();
		if (photo != null) {
			outputStream.write(photo);
		} else {
			outputStream.write(new byte[] {});
		}
		outputStream.flush();
		outputStream.close();
	}

	@GetMapping("/gallery")
	public String showGalleryDashboard() {
		return "gallery";
	}

}
