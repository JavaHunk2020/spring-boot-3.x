package com.kuebiko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.SignupService;

@Controller
public class CreditCardController {
	
	@Autowired
	private SignupService signupService;
	
	  @GetMapping("/applyForCreditCard")
		public String showCardPage(@RequestParam int sid,Model model) {
		     SignupDTO signupDTO=signupService.findBySid(sid).get();
		     model.addAttribute("signupDTO", signupDTO);
			 return "applyCreditCard";
		}
	  
	  @PostMapping("/applyForCreditCard")
		public String createCardDetails(@ModelAttribute CreditCardDTO creditCardDTO,Model model) {
		  creditCardDTO.setApplicationId("CAS0192392");
		  creditCardDTO.setStatus("PENDING");
		  creditCardDTO
			 return "redirect:/showData";
		}

}
