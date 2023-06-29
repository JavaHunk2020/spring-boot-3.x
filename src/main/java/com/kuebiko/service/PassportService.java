package com.kuebiko.service;

import java.util.Optional;

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
	
	public void deleteById(int pid) {
		passportRepository.deleteById(pid);
	}
	
	public PassportDTO findBySignupId(int sid) {
		PassportDTO passportDTO=new PassportDTO();
		 Optional<PassportEntity> optional=passportRepository.findBySignupEntityId(sid);
		 if(optional.isPresent()) {
			 BeanUtils.copyProperties(optional.get(),passportDTO);
		 }
		 return passportDTO;
	}
	
	public void save(PassportDTO passportDTO) {
		PassportEntity passportEntity=new PassportEntity();
		BeanUtils.copyProperties(passportDTO, passportEntity,new String[] {"sid"});
		//THIS IS THERE ALREADY IN DB
		SignupEntity sentity=signupRepository.findById(passportDTO.getSid()).get();
		passportEntity.setSignupEntity(sentity);
		//passport entity says I will not go inside database with out singup entity
		//since I have dependency on signup entity
		passportRepository.save(passportEntity);
	}
	
	public void saveByParentEmail(PassportDTO passportDTO) {
		PassportEntity passportEntity=new PassportEntity();
		BeanUtils.copyProperties(passportDTO, passportEntity,new String[] {"sid","email"});
		//THIS IS THERE ALREADY IN DB
		SignupEntity sentity=signupRepository.findByEmailOrName(passportDTO.getEmail(),passportDTO.getEmail()).get();
		passportEntity.setSignupEntity(sentity);
		//passport entity says I will not go inside database with out singup entity
		//since I have dependency on signup entity
		passportRepository.save(passportEntity);
	}

}
