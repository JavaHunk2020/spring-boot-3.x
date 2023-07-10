package com.kuebiko.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuebiko.controller.dto.CreditCardTypeDTO;
import com.kuebiko.dao.CreditCardTypesRepository;
import com.kuebiko.dao.entity.CreditCardType;

@Service
public class CreditCardTypeService {
	
	@Autowired
	private CreditCardTypesRepository cardTypesRepository;
	
	
	public List<CreditCardTypeDTO> findAll(){
		  List<CreditCardType> crediCardList=  cardTypesRepository.findAll();
		  
		  List<CreditCardTypeDTO> list=new ArrayList<CreditCardTypeDTO>();
		  for(CreditCardType crt : crediCardList) {
			  CreditCardTypeDTO cardTypeDTO=new CreditCardTypeDTO();
			  BeanUtils.copyProperties(crt, cardTypeDTO);
			  list.add(cardTypeDTO);
		  }
		  return list;
	}
	
	public byte[] findById(int cctid){
		  CreditCardType creditCardType=  cardTypesRepository.findById(cctid).get();
	      CreditCardTypeDTO cardTypeDTO=new CreditCardTypeDTO();
		  BeanUtils.copyProperties(creditCardType, cardTypeDTO);
		  return cardTypeDTO.getPhoto();
	}


}
