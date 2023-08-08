package com.kuebiko.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kuebiko.dao.GalleryRepository;
import com.kuebiko.dao.entity.GalleryEntity;
@RestController
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
@RequestMapping("/v1/gallery")
public class GalleryRestController {

	@Autowired
	private GalleryRepository galleryRepository;

	@PostMapping
	public AppResponse postGallery(MultipartFile photo, int gid) throws IOException {
		GalleryEntity galleryEntity = new GalleryEntity();
		if(gid==0) {
			gid =galleryRepository.getMaxId()+1;
		}
		galleryEntity.setId(gid);
		galleryEntity.setDoe(new Timestamp(new Date().getTime()));
		galleryEntity.setName(photo.getOriginalFilename());
		galleryEntity.setSize(photo.getSize());
		galleryEntity.setPhoto(photo.getBytes());
		galleryRepository.save(galleryEntity);
		
		AppResponse appResponse=new AppResponse();
		appResponse.setCode("200");
		appResponse.setMessage("Passport details is deleted successfully.");
		
		return  appResponse;
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

	@GetMapping
	public Map<String,List<GalleryEntity>> showGalleryDashboard(Model model) {
		
		Map<String,List<GalleryEntity>> mapped =new LinkedHashMap<>();
		List<GalleryEntity> list=galleryRepository.findAll();
		
		int row=1;
		for (int x = 0; x < list.size(); x = x + 4) {
			List<GalleryEntity> tlist = new ArrayList<GalleryEntity>();
			tlist.add(list.get(x)); // 4
			if ((x + 1) < list.size()) {
				tlist.add(list.get(x + 1)); // 5
			}
			if ((x + 2) < list.size()) {
				tlist.add(list.get(x + 2)); // 6
			}
			if ((x + 3) < list.size()) {
				tlist.add(list.get(x + 3)); // 7
			}
			mapped.put(row + "", tlist);
			row++;
		}
		//1 ->> 0,1,2,3
		//2=> 4,5,6,7
		return mapped;
	}

}
