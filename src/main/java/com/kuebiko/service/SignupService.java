package com.kuebiko.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuebiko.dao.CreditCardApplicationRepository;
import com.kuebiko.dao.LoginHistoryRepository;
import com.kuebiko.dao.PassportRepository;
import com.kuebiko.dao.SignupRepository;
import com.kuebiko.dao.entity.CreditCardApplicationEntity;
import com.kuebiko.dao.entity.LoginHistoryEntity;
import com.kuebiko.dao.entity.PassportEntity;
import com.kuebiko.dao.entity.SignupEntity;
import com.kuebiko.dto.LoginHistoryDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.rest.controller.Role;


//This annotation is used to create a bean of service layer
@Service
public class SignupService {
	
	@Autowired
	private SignupRepository  signupRepository;
	
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;
	
	@Autowired
	private PassportRepository passportRepository;
	
	@Autowired
	private CreditCardApplicationRepository cardApplicationRepository;
	
	
	public void  deleteBySid(int sid) {
		//signupRepository.deleteById(sid);
		 Optional<PassportEntity> optional=passportRepository.findBySignupEntityId(sid);
		 if(optional.isPresent()) {
			 passportRepository.delete(optional.get());
		 }else {
			 signupRepository.deleteById(sid);
		 }
	}
	
	public void persist(SignupDTO signupDTO) {
		  //  /WEB-INF/login.jsp
		   //JDBC PROGRAMMING
		signupDTO.setRole(Role.CUSTOMER.getValue());
		SignupEntity  entity=new SignupEntity();
		BeanUtils.copyProperties(signupDTO, entity);
		entity.setDoe(new Timestamp(new Date().getTime()));
		entity.setDom(new Timestamp(new Date().getTime()));
		signupRepository.save(entity);
	}
	
	public List<SignupDTO>  findAllByRole(String role) {
		 List<SignupEntity> entityList=signupRepository.findByRole(role);
		 return convertIntoDTO(entityList);
	}
	
	public List<SignupDTO>  findAll() {
		 List<SignupEntity> entityList=signupRepository.findAll();
		 return convertIntoDTO(entityList);
	}
	
	private List<SignupDTO> convertIntoDTO( List<SignupEntity> entityList){
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
			 List<CreditCardApplicationEntity> creditCardApplicationList=cardApplicationRepository.findByEmail(entity.getEmail());
			 if(creditCardApplicationList.size()>0) {
				 for(CreditCardApplicationEntity card : creditCardApplicationList) {
					 dto.setCardStatus(card.getStatus());
					 dto.setCardName(card.getCardName());
					 dto.setApplicationId(card.getApplicationId());
					 dto.setApplyDate(card.getDoa());
					 dto.setCreditCardFlag("yes");
					 SignupDTO newdto=new SignupDTO();
					 BeanUtils.copyProperties(dto, newdto);
					 dtosList.add(newdto);
				 }
			 }else {
				 dto.setCreditCardFlag("no");
				 dtosList.add(dto);
			 }
			 
			
		 }
		 return dtosList;
	}
	
	public Optional<SignupDTO> findByEmailAndPassword(String email,String password) {
		Optional<SignupEntity> optional=signupRepository.findByEmailAndPassword(email,password);
		SignupDTO signupDTO = null;
		if (optional.isPresent()) {
			signupDTO=new SignupDTO();
			BeanUtils.copyProperties(optional.get(), signupDTO);
		}
		// Optional - class which was introduce java8 -2014
		return Optional.ofNullable(signupDTO);
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
	
	
	//Optional<SignupEntity> - >> Optional<SignupDTO>
		public Optional<SignupDTO> findBySid(int sid) {
			Optional<SignupEntity> optional=signupRepository.findById(sid);
			SignupDTO signupDTO = null;
			if (optional.isPresent()) {
				signupDTO=new SignupDTO();
				BeanUtils.copyProperties(optional.get(), signupDTO);
			}
			// Optional - class which was introduce java8 -2014
			return Optional.ofNullable(signupDTO);
		}
	
	//Optional<SignupEntity> - >> Optional<SignupDTO>
	public Optional<SignupDTO> findByEmail(String email) {
		Optional<SignupEntity> optional=signupRepository.findByEmail(email);
		SignupDTO signupDTO = null;
		if (optional.isPresent()) {
			signupDTO=new SignupDTO();
			BeanUtils.copyProperties(optional.get(), signupDTO);
			 Optional<PassportEntity> poptional=passportRepository.findBySignupEntityId(signupDTO.getSid());
			 if(poptional.isPresent()) {
				 signupDTO.setPassportFlag("yes");
				 signupDTO.setPhoto(optional.get().getPhoto());
			 }else {
				 signupDTO.setPassportFlag("no");
			 }
			
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
	
	public List<SignupDTO> findRecords(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<SignupEntity> page = signupRepository.findAll(pageable);
		List<SignupEntity> products = page.getContent();
		List<SignupDTO> dtosList = new ArrayList<SignupDTO>();
		for (SignupEntity entity : products) {
			SignupDTO dto = new SignupDTO();
			BeanUtils.copyProperties(entity, dto);
			Optional<PassportEntity> optional = passportRepository.findBySignupEntityId(entity.getSid());
			if (optional.isPresent()) {
				dto.setPassportFlag("yes");
				dto.setPhoto(optional.get().getPhoto());
			} else {
				dto.setPassportFlag("no");
			}
			dtosList.add(dto);
		}
		return dtosList;
	}

	@Transactional
	public void updatePassword(String email,String password) {
		//hid - hisotry database id
		SignupEntity entity=signupRepository.findByEmail(email).get();
		entity.setPassword(password);
	}
	
	public void updatePasswordByEmail(String email, String newPassword) {
		signupRepository.updatePasswordByEmail(newPassword, email);
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
