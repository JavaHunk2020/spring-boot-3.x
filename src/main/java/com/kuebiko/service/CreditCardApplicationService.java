package com.kuebiko.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.dao.CreditCardApplicationRepository;
import com.kuebiko.dao.CreditCardDetailRepository;
import com.kuebiko.dao.entity.CreditCardApplicationEntity;
import com.kuebiko.dao.entity.CreditCardDetailEntity;
import com.kuebiko.dto.PatchDTO;
import com.kuebiko.rest.controller.ImageResponse;
import com.kuebiko.utils.CreditCardUtils;

@Service
public class CreditCardApplicationService {
	
	@Autowired
	private CreditCardApplicationRepository creditCardApplicationRepository;
	
	@Autowired
	private CreditCardDetailRepository creditCardDetailRepository;
	
	
	public List<ImageResponse> findCreditCardsByEmail(String email) {
		 List<ImageResponse> list =new  ArrayList<>();
		List<CreditCardDetailEntity> entityList=creditCardDetailRepository.findByEmail(email);
		for(CreditCardDetailEntity entity : entityList) {
			ImageResponse imageResponse=new ImageResponse();
			imageResponse.setPhoto(entity.getPhoto());
			list.add(imageResponse);
		}
		 return list;
	}
	
	public byte[] findCreditCardDetails(String applicationId) {
		 Optional<CreditCardDetailEntity> optional=creditCardDetailRepository.findByApplicationId(applicationId);
		 if(optional.isPresent()) {
			 return optional.get().getPhoto();
		 }
		 return new byte[] {};
	}
	
	@Transactional
	public void saveCardDetails(CreditCardDTO creditCardDTO) {
		
		 Optional<CreditCardDetailEntity> optional=creditCardDetailRepository.findByApplicationId(creditCardDTO.getApplicationId());
		if(optional.isPresent()) {
			CreditCardDetailEntity creditCardDetailEntity=optional.get();
			creditCardDetailEntity.setCvv(creditCardDTO.getCvv());
			creditCardDetailEntity.setNumber(creditCardDTO.getNumber());
			creditCardDetailEntity.setExpDate(creditCardDTO.getExpDate());
			creditCardDetailEntity.setPhoto(creditCardDTO.getPhoto());
			creditCardDetailEntity.setDom(CreditCardUtils.getCurrentTime());
		}else {
			CreditCardDetailEntity scardDetailEntity=new CreditCardDetailEntity();
			CreditCardDTO  db=this.findByApplicationId(creditCardDTO.getApplicationId());
			BeanUtils.copyProperties(db, scardDetailEntity);	
			scardDetailEntity.setCvv(creditCardDTO.getCvv());
			scardDetailEntity.setNumber(creditCardDTO.getNumber());
			scardDetailEntity.setExpDate(creditCardDTO.getExpDate());
			scardDetailEntity.setPhoto(creditCardDTO.getPhoto());
			scardDetailEntity.setDoa(CreditCardUtils.getCurrentTime());
			scardDetailEntity.setDom(CreditCardUtils.getCurrentTime());
			creditCardDetailRepository.save(scardDetailEntity);
		}
		CreditCardApplicationEntity entity =creditCardApplicationRepository.findByApplicationId(creditCardDTO.getApplicationId());
		entity.setStatus("Card Generated");
	}
	
	public String save(CreditCardDTO creditCardDTO) {
		CreditCardApplicationEntity entity=new CreditCardApplicationEntity();
		BeanUtils.copyProperties(creditCardDTO, entity);
		entity.setDoa(CreditCardUtils.getCurrentTime());
		entity.setDom(CreditCardUtils.getCurrentTime());
		creditCardApplicationRepository.save(entity);
		return "A01292389282";
	}
	
	public CreditCardDTO generatedCreditCard(String name,String email) {
		return generatedCreditCard(name);
	}
	
	private CreditCardDTO generatedCreditCard(String name) {
		CreditCardDTO cardDTO=new CreditCardDTO();
		String cardNumber=generateCreditCardNumber();
		cardDTO.setNumber(cardNumber);
		String exp=generateCreditCardExpireDate();
		cardDTO.setExpDate(exp);
		byte[] photo = new byte[]{};
		
		Resource resource = new ClassPathResource("images/credit-card-front-template.jpg");
		
		try {
			InputStream resourceInputStream = resource.getInputStream();
			
			Image src = ImageIO.read(resourceInputStream);

			int wideth = src.getWidth(null);
			int height = src.getHeight(null);

			BufferedImage tag = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = tag.createGraphics();

			g.setBackground(new Color(200, 250, 200));
			g.clearRect(0, 0, wideth, height);
			g.setColor(Color.WHITE);
			g.drawImage(src, 0, 0, wideth, height, null);
			
			// credit card number
			g.setFont(new Font("Lucida Console", Font.BOLD, 36));
			g.drawString(cardNumber.substring(0, 4), 40, 207);
			g.drawString(cardNumber.substring(4, 8), 150, 207);
			g.drawString(cardNumber.substring(8, 12), 260, 207);
			g.drawString(cardNumber.substring(12, 16), 370, 207);
			
			// exp date
			g.setFont(new Font("Lucida Console", Font.PLAIN, 24));
			g.drawString(exp, 65, 250);

			// customer name
			g.setFont(new Font("Tahoma", Font.PLAIN, 28));
			g.drawString(name.toUpperCase(), 30, 290);
			
			//cardType
			g.setFont(new Font("Lucida Console",Font.ITALIC,20));
			g.drawString("VISA", 120, 20);
			
			//load new image
			Resource simage = new ClassPathResource("images/logo.png");
			InputStream simageInputStream = simage.getInputStream();
			Image img = ImageIO.read(simageInputStream);
			//Draw image on given card
			g.drawImage(img, 304, 255, 91, 45, null);

			g.dispose();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(tag, "jpg", baos);
			baos.flush();
			photo= baos.toByteArray();
			cardDTO.setPhoto(photo);
			cardDTO.setCvv(Integer.parseInt(generateCCVNumber()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cardDTO;
	}
	
	private  String generateCreditCardNumber() {
		Random random = new Random();
		StringBuilder number = new StringBuilder();
		number.append(String.format("%04d", random.nextInt(10000)));
		number.append(String.format("%04d", random.nextInt(10000)));
		number.append(String.format("%04d", random.nextInt(10000)));
		number.append(String.format("%04d", random.nextInt(10000)));
		return number.toString();
	}

	private String generateCreditCardExpireDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		LocalDate date = LocalDate.now();
		date = date.plusYears(3);
		return formatter.format(date);
	}
	
	private String generateCCVNumber() {
		Random random = new Random();
		StringBuilder number = new StringBuilder();
		number.append(String.format("%03d", random.nextInt(1000)));
		return number.toString();
	}
	
	
	
	@Transactional
	public void changeStatus(PatchDTO patchDTO) {
		CreditCardApplicationEntity entity =creditCardApplicationRepository.findByApplicationId(patchDTO.getAttribute());
		entity.setStatus(patchDTO.getValue());
	}
	
	public boolean isEmailExists(String email) {
		return creditCardApplicationRepository.existsByEmail(email);
	}

	public CreditCardDTO findByApplicationId(String applicationId) {
		CreditCardApplicationEntity entity =creditCardApplicationRepository.findByApplicationId(applicationId);
		CreditCardDTO creditCardDTO=new CreditCardDTO();
		BeanUtils.copyProperties(entity, creditCardDTO);
		return creditCardDTO;
	}

}
