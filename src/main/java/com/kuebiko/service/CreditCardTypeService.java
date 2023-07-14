package com.kuebiko.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuebiko.controller.dto.CreditCardTypeDTO;
import com.kuebiko.dao.CreditCardApplicationRepository;
import com.kuebiko.dao.CreditCardTypesRepository;
import com.kuebiko.dao.entity.CreditCardApplicationEntity;
import com.kuebiko.dao.entity.CreditCardType;

@Service
public class CreditCardTypeService {
	
	@Autowired
	private CreditCardTypesRepository cardTypesRepository;
	
	@Autowired
	private CreditCardApplicationRepository creditCardApplicationRepository;
	
	public List<CreditCardTypeDTO> findAll(int sid) {
		  List<CreditCardType> crediCardList=  cardTypesRepository.findAll();
		  List<CreditCardApplicationEntity> applicationEntities=creditCardApplicationRepository.findBySid(sid);
		  List<CreditCardTypeDTO> list=new ArrayList<CreditCardTypeDTO>();
		  for(CreditCardType crt : crediCardList) {
			  CreditCardTypeDTO cardTypeDTO=new CreditCardTypeDTO();
			  BeanUtils.copyProperties(crt, cardTypeDTO);
			  
			  for(CreditCardApplicationEntity app : applicationEntities) {
				    if(crt.getName().equalsIgnoreCase(app.getCardName())) {
				    	  cardTypeDTO.setAlreadyApplied("Yes");
				    	  cardTypeDTO.setStatus(app.getStatus());
				    	  break;
				    }
			  }
			  list.add(cardTypeDTO);
		  }
		  return list;
	}
	
	public byte[] findById(int cctid){
		  CreditCardType creditCardType=  cardTypesRepository.findById(cctid).get();
		  return creditCardType.getPhoto();
	}

	public void save(CreditCardTypeDTO creditCardTypeDTO) {
		CreditCardType entity = new CreditCardType();
		BeanUtils.copyProperties(creditCardTypeDTO, entity);
		entity.setDoe(new Timestamp(new Date().getTime()));
		entity.setDom(new Timestamp(new Date().getTime()));		
		// Set unique card type number
		Random random = new Random();
		StringBuilder number = new StringBuilder();
		number.append(String.format("%03d", random.nextInt(1000)));
		entity.setCcode("cardType-"+number);
		cardTypesRepository.save(entity);
	}

}
