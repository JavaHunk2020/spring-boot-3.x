package com.kuebiko.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.dao.CreditCardApplicationRepository;
import com.kuebiko.dao.entity.CreditCardApplicationEntity;
import com.kuebiko.utils.CreditCardUtils;

@Service
public class CreditCardApplicationService {
	
	@Autowired
	private CreditCardApplicationRepository creditCardApplicationRepository;
	
	public String save(CreditCardDTO creditCardDTO) {
		CreditCardApplicationEntity entity=new CreditCardApplicationEntity();
		BeanUtils.copyProperties(creditCardDTO, entity);
		entity.setDoa(CreditCardUtils.getCurrentTime());
		entity.setDom(CreditCardUtils.getCurrentTime());
		creditCardApplicationRepository.save(entity);
		return "A01292389282";
	}
	
	public boolean isEmailExists(String email) {
		return creditCardApplicationRepository.existsByEmail(email);
	}

}
