package com.kuebiko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.rest.controller.AppResponse;
import com.kuebiko.service.CreditCardApplicationService;
import com.kuebiko.service.SignupService;
import com.kuebiko.utils.CreditCardStatus;
import com.kuebiko.utils.CreditCardUtils;

@Controller
public class CreditCardController {
	
	@Autowired
	private SignupService signupService;
	
	@Autowired
	private CreditCardApplicationService cardApplicationService;
	
	  @GetMapping("/applyForCreditCard")
		public String showCardPage(@RequestParam int sid,Model model) {
		     SignupDTO signupDTO=signupService.findBySid(sid).get();
		     model.addAttribute("signupDTO", signupDTO);
			 return "applyCreditCard";
		}
	  
	  @PostMapping("/applyForCreditCard")
		public String createCardDetails(@ModelAttribute CreditCardDTO creditCardDTO,RedirectAttributes redirectAttributes,Model model) {
			AppResponse appResponse=new AppResponse();
		    if(cardApplicationService.isEmailExists(creditCardDTO.getEmail())) {
		    	appResponse.setCode("0");
		    	appResponse.setMessage("It seems like you have already applied for credit card");
		    }else {
		    	creditCardDTO.setStatus(CreditCardStatus.PENDING.getValue());
			    String appNumber="ATO"+CreditCardUtils.genRandNum();
				creditCardDTO.setApplicationId(appNumber);
				cardApplicationService.save(creditCardDTO);
				appResponse.setCode("12");
				appResponse.setMessage("Credit card application submitted successfully. Your application number is = "+appNumber);
		    }
		    redirectAttributes.addFlashAttribute("appResponse", appResponse);
		    return "redirect:/capplicationStatus";
		}
	  
	   @GetMapping("/capplicationStatus")
	   public String goToSuccessPage() {
		    return "applicationStatus";
	   }

}
