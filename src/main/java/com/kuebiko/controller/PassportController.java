package com.kuebiko.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuebiko.dto.LoginHistoryDTO;
import com.kuebiko.dto.PassportDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.PassportService;
import com.kuebiko.service.SignupService;

@Controller
@RequestMapping("/action")
public class PassportController {
	
	@Autowired
	private PassportService passportService;

	@Autowired
	private SignupService signupService;
	
  @GetMapping("/addPassport")
	public String showPassport(@RequestParam int sid) {
		return "passport";
	}
  
  
  @GetMapping("/loginHistory")
	public String showLoginHistory(HttpSession session,Model  model) {
	   SignupDTO signupDTO=(SignupDTO)session.getAttribute("userLoggedIn");
	   List<LoginHistoryDTO> dtos=signupService.findAllHistory(signupDTO.getSid());
	   model.addAttribute("loginHistoryList", dtos);
	   //WRITE LOGIC
		List<SignupDTO>  signupDTOs=signupService.findAll();
		model.addAttribute("bananas", signupDTOs);
		 return "signups";
	}
  
  @GetMapping("/passportDetails")
 	public String showPassportDetails(@RequestParam int sid,HttpSession session,Model model) {
	     
	   PassportDTO passportDTO=passportService.findBySignupId(sid);
	   model.addAttribute("passportDetails", passportDTO);
	   
	   
	 //Fetching data from session
		SignupDTO signupDTO=(SignupDTO)session.getAttribute("userLoggedIn");
		if(signupDTO==null) {
			 model.addAttribute("message","It seems like session is expired!");
			 return "login";
		}else {
			String currentRole=signupDTO.getRole();
			if(currentRole.equals("Admin")) {
				List<SignupDTO>  signupDTOs=signupService.findAllByRole("Customer");
				model.addAttribute("bananas", signupDTOs);
			}else {
				Optional<SignupDTO> optional=signupService.findByEmail(signupDTO.getEmail());
				List<SignupDTO> dtos=new ArrayList<SignupDTO>();
				dtos.add(optional.get());
				model.addAttribute("bananas", dtos);
			}
		}
		 return "signups";
 	}
  
  
   @PostMapping("/addPassport")
	public String postPassport(@RequestParam int sid,@RequestParam String number,@RequestParam String name,@RequestParam String address,@RequestParam String exp,@RequestParam String photo) throws ParseException {
	   
	   PassportDTO dto=new PassportDTO();
	   dto.setAddress(address);
	   dto.setName(name);
	   dto.setNumber(number);
	   DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	   Date expDate = df.parse(exp); //exp 
	   dto.setExp(expDate);
	   dto.setDoe(new Date());
	   dto.setSid(sid);
	   dto.setPhoto(photo);
	   
	   passportService.save(dto);
		return "redirect:/action/showData";
	}
  
} 