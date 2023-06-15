package com.kuebiko.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuebiko.dto.PassportDTO;
import com.kuebiko.service.PassportService;

@Controller
public class PassportController {
	
	@Autowired
	private PassportService passportService;

	
  @GetMapping("/addPassport")
	public String showPassport(@RequestParam int sid) {
		return "passport";
	}
  
   @PostMapping("/addPassport")
	public String postPassport(@RequestParam int sid,@RequestParam String number,@RequestParam String name,@RequestParam String address,String exp) throws ParseException {
	   
	   PassportDTO dto=new PassportDTO();
	   dto.setAddress(address);
	   dto.setName(name);
	   dto.setNumber(number);
	   DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	   Date expDate = df.parse(exp); //exp 
	   dto.setExp(expDate);
	   dto.setDoe(new Date());
	   dto.setSid(sid);
	   
	   passportService.save(dto);
		return "redirect:/showData";
	}
  
} 