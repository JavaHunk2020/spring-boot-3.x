package com.kuebiko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuebiko.dao.SignupDao;
import com.kuebiko.dao.entity.SignupEntity;
import com.kuebiko.dto.SignupDTO;


//This annotation is used to create a bean of service layer
@Service
public class SignupService {
	
	@Autowired
	private SignupDao signupDao;
	
	public void  deleteBySid(int sid) {
	  	signupDao.deleteBySid(sid);
	}
	
	public void persist(String username ,String email, String gender) {
		  //  /WEB-INF/login.jsp
		   //JDBC PROGRAMMING
		  signupDao.save(username, email, gender);
	}
	
	public List<SignupDTO>  findAll() {
		 List<SignupEntity> entityList=signupDao.findAll();
		 List<SignupDTO> dtosList=new ArrayList<SignupDTO>();
		 for(SignupEntity entity : entityList) {
			 SignupDTO dto=new SignupDTO();
			 BeanUtils.copyProperties(entity, dto);
			 dtosList.add(dto);
		 }
		 return dtosList;
	}
	
	
	//Optional<SignupEntity> - >> Optional<SignupDTO>
	public Optional<SignupDTO> findByName(String name) {
		Optional<SignupEntity> optional=signupDao.findByUsername(name);
		SignupDTO signupDTO = null;
		if (optional.isPresent()) {
			BeanUtils.copyProperties(optional.get(), signupDTO);
		}
		// Optional - class which was introduce java8 -2014
		return Optional.ofNullable(signupDTO);
	}

}
