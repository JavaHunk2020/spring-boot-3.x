package com.kuebiko.rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.PassportDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.security.jwt.JwtUtils;
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
		public List<SignupDTO> showSignups(@RequestParam String role, @RequestParam String email) {
			// WRITE LOGIC
			List<SignupDTO> signupDTOs = new ArrayList<>();
			if (role.equalsIgnoreCase("admin")) {
				signupDTOs = signupService.findAllByRole("customer");
			} else {
				Optional<SignupDTO> optional = signupService.findByEmail(email);
				if (optional.isPresent()) {
					signupDTOs.add(optional.get());
				}
			}
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
	
	
	  @Autowired
	  AuthenticationManager authenticationManager;


	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	
	
	 /**
	  * 	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  * @param signupRequest
	  * @return
	  */
	@PostMapping("/cauth")
	public AppResponse  postLogin(@RequestBody SignupRequest signupRequest) {
		//authentication has two things - username and role
		
		//UserDetailsServiceImpl - 
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signupRequest.getUsername(), signupRequest.getPassword()));
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		SignupDTO signupDTO=signupService.findByName(signupRequest.getUsername()).get();
		AppResponse appResponse=new AppResponse();
		appResponse.setCid(signupDTO.getHid());
		appResponse.setEmail(signupDTO.getEmail());
		appResponse.setMessage("token is generated");
		appResponse.setRole(signupDTO.getRole());
		appResponse.setAuthorization(jwt);
		appResponse.setName(signupRequest.getUsername());
		appResponse.setCid(0);
		return appResponse;
	}

}
