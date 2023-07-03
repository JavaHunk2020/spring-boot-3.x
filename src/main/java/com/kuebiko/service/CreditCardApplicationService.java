package com.kuebiko.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.dao.CreditCardApplicationRepository;
import com.kuebiko.dao.entity.CreditCardApplicationEntity;
import com.kuebiko.dto.PatchDTO;
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
	
	@Transactional
	public void changeStatus(PatchDTO patchDTO) {
		CreditCardApplicationEntity entity =creditCardApplicationRepository.findByEmail(patchDTO.getAttribute()).get();
		entity.setStatus(patchDTO.getValue());
	}
	
	public CreditCardDTO findByEmailId(String emailId) {
		CreditCardApplicationEntity entity =creditCardApplicationRepository.findByEmail(emailId).get();
		CreditCardDTO creditCardDTO=new CreditCardDTO();
		BeanUtils.copyProperties(entity, creditCardDTO);
		return creditCardDTO;
	}
	
	public boolean isEmailExists(String email) {
		return creditCardApplicationRepository.existsByEmail(email);
	}

}
