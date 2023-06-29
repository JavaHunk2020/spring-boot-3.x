package com.kuebiko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.dao.CreditCardApplicationRepository;

@Service
public class CreditCardApplicationService {
	
	@Autowired
	private CreditCardApplicationRepository creditCardApplicationRepository;
	
	public String save(CreditCardDTO creditCardDTO) {
		
		return "A01292389282";
	}

}
