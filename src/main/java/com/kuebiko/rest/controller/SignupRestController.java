package com.kuebiko.rest.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.PassportDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.PassportService;
import com.kuebiko.service.SignupService;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
public class SignupRestController {
	
	@Autowired
	private SignupService signupService;
	
	@Autowired
	private PassportService passportService;
	
	
	@PostMapping("/passports")
	public AppResponse createPassport(@RequestBody PassportDTO passportDTO) throws ParseException {
		passportDTO.setDoe(new Date());
		passportService.saveByParentEmail(passportDTO);
		return createResponse("success", "Passport entry is credted succesfully.");
	}
	 
	 
	  private AppResponse createResponse(String status, String message) {
		   AppResponse appResponse=new AppResponse();
			appResponse.setCode(status);
			appResponse.setMessage(message);
			return appResponse;
	  }
	
	
	
	@GetMapping("/signups")
	public List<SignupDTO> showSignups() {
		//WRITE LOGIC
			List<SignupDTO>  signupDTOs=signupService.findAll();
			return signupDTOs;
	}
	
	
	@PostMapping("/csignup")
	public AppResponse createSignup(@RequestParam String username, 
			@RequestParam String gender,@RequestParam String email) {
		    //below method will save data inside database
			SignupDTO dto=new SignupDTO();
			dto.setName(username);
			dto.setGender(gender);
			dto.setEmail(email);
		    signupService.persist(dto);
			return createResponse("success","Signup is done successfully.");
	}
	
	
	@PostMapping("/cauth")
	public AppResponse  postLogin(@RequestBody SignupRequest signupRequest) {
		AppResponse appResponse=new AppResponse();
		Optional<SignupDTO> optional=signupService.findByName(signupRequest.getUsername());
		if(optional.isPresent()) {
			//Hey user is there
			//Create session object and add user details
			appResponse.setCode("success");
			appResponse.setMessage("Username and password are correct");
		}else {
			appResponse.setCode("fail");
			appResponse.setMessage("Hmmm I hate you!");
		}
		  return appResponse;
	}

}
