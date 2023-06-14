package com.kuebiko.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuebiko.dao.PassportRepository;
import com.kuebiko.dao.SignupRepository;
import com.kuebiko.dao.entity.PassportEntity;
import com.kuebiko.dao.entity.SignupEntity;
import com.kuebiko.dto.PassportDTO;

@Service
public class PassportService {
	
	@Autowired
	private SignupRepository signupRepository;
	
	@Autowired
	private PassportRepository passportRepository;
	
	public void save(PassportDTO passportDTO) {
		PassportEntity entity=new PassportEntity();
		BeanUtils.copyProperties(passportDTO, entity,new String[] {"sid"});
		
		//THIS IS THERE ALREADY IN DB
		SignupEntity sentity=signupRepository.findById(passportDTO.getSid()).get();
		entity.setSignupEntity(sentity);
		passportRepository.save(entity);
	}

}
