package com.kuebiko.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuebiko.dao.LoginHistoryRepository;
import com.kuebiko.dao.PassportRepository;
import com.kuebiko.dao.SignupRepository;
import com.kuebiko.dao.entity.LoginHistoryEntity;
import com.kuebiko.dao.entity.PassportEntity;
import com.kuebiko.dao.entity.SignupEntity;
import com.kuebiko.dto.LoginHistoryDTO;
import com.kuebiko.dto.SignupDTO;


//This annotation is used to create a bean of service layer
@Service
public class SignupService {
	
	@Autowired
	private SignupRepository  signupRepository;
	
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;
	
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
	
	
	@Transactional
	public void updateLogoutTime(int hid) {
		//hid - hisotry database id
		LoginHistoryEntity loginHistoryEntity=loginHistoryRepository.findById(hid).get();
		loginHistoryEntity.setLogouttime(new Timestamp(new Date().getTime()));
	}
	
	public List<LoginHistoryDTO> findAllHistory(int sid) {
		List<LoginHistoryEntity> historyEntities=loginHistoryRepository.findAllBySignupId(sid);
		 List<LoginHistoryDTO> dtosList=new ArrayList<LoginHistoryDTO>();
		 for(LoginHistoryEntity entity : historyEntities) {
			 LoginHistoryDTO dto=new LoginHistoryDTO();
			 BeanUtils.copyProperties(entity, dto);
			 dtosList.add(dto);
		 }
		 return dtosList;
	}
	
	public int saveLoginHistory(int sid) {
		SignupEntity signupEntity=signupRepository.findById(sid).get();
		LoginHistoryEntity entity=new LoginHistoryEntity();
		entity.setLtime(new Timestamp(new Date().getTime()));
		entity.setName(signupEntity.getName());
		entity.setSignupEntity(signupEntity);
		LoginHistoryEntity  result=loginHistoryRepository.save(entity);
		return result.getId();
	}

}
