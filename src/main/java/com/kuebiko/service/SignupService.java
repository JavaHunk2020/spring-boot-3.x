package com.kuebiko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuebiko.dao.PassportRepository;
import com.kuebiko.dao.SignupRepository;
import com.kuebiko.dao.entity.PassportEntity;
import com.kuebiko.dao.entity.SignupEntity;
import com.kuebiko.dto.SignupDTO;


//This annotation is used to create a bean of service layer
@Service
public class SignupService {
	
	@Autowired
	private SignupRepository  signupRepository;
	
	@Autowired
	private PassportRepository passportRepository;
	
	public void  deleteBySid(int sid) {
		//signupRepository.deleteById(sid);
		 Optional<PassportEntity> optional=passportRepository.findBySignupEntityId(sid);
		 if(optional.isPresent()) {
			 passportRepository.delete(optional.get());
		 }else {
			 signupRepository.deleteById(sid);
		 }
	}
	
	public void persist(String username ,String email, String gender) {
		  //  /WEB-INF/login.jsp
		   //JDBC PROGRAMMING
		SignupEntity  entity=new SignupEntity();
		entity.setEmail(email);
		entity.setGender(gender);
		entity.setName(username);
		signupRepository.save(entity);
	}
	
	public List<SignupDTO>  findAll() {
		 List<SignupEntity> entityList=signupRepository.findAll();
		 List<SignupDTO> dtosList=new ArrayList<SignupDTO>();
		 for(SignupEntity entity : entityList) {
			 SignupDTO dto=new SignupDTO();
			 BeanUtils.copyProperties(entity, dto);
			 Optional<PassportEntity> optional=passportRepository.findBySignupEntityId(entity.getSid());
			 if(optional.isPresent()) {
				 dto.setPassportFlag("yes");
				 dto.setPhoto(optional.get().getPhoto());
			 }else {
				 dto.setPassportFlag("no");
			 }
			 
			 dtosList.add(dto);
		 }
		 return dtosList;
	}
	
	
	//Optional<SignupEntity> - >> Optional<SignupDTO>
	public Optional<SignupDTO> findByName(String name) {
		Optional<SignupEntity> optional=signupRepository.findByName(name);
		SignupDTO signupDTO = null;
		if (optional.isPresent()) {
			signupDTO=new SignupDTO();
			BeanUtils.copyProperties(optional.get(), signupDTO);
		}
		// Optional - class which was introduce java8 -2014
		return Optional.ofNullable(signupDTO);
	}

}
