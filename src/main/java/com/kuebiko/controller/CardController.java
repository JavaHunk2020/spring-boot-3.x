package com.kuebiko.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.kuebiko.controller.dto.CreditCardTypeDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.CreditCardTypeService;

@Controller
public class CardController {
	
	@Autowired
	private CreditCardTypeService creditCardTypeService;
	
	
	@GetMapping("/cimage")
	public void loadImage(@RequestParam int ctid,HttpServletResponse response) throws IOException {
		//Fetch photo
  	   byte[] photo = creditCardTypeService.findById(ctid);
	   response.setContentType("image/png");
	   ServletOutputStream outputStream=response.getOutputStream();
	   if(photo!=null) {
		   outputStream.write(photo);
	   }else {
		   outputStream.write(new byte[] {});
	   }
	   outputStream.flush();
	   outputStream.close();
	}
	
	@GetMapping("/addNewCard")
	public String addCard(Model model) {
		return  "addNewCard";
	}
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws ServletException {
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
	
	@PostMapping("/addNewCard")
	public String saveCard(@ModelAttribute CreditCardTypeDTO creditCardTypeDTO,Model model) throws IOException {
		System.out.println("The values from the credit card type form are: "+creditCardTypeDTO);
		creditCardTypeService.save(creditCardTypeDTO);
		return "redirect:/showCards";
	}

	@GetMapping("/showCards")
	public String showCards(Model model,HttpSession session) {
		   SignupDTO signupDTO=(SignupDTO)session.getAttribute("userLoggedIn");
		List<CreditCardTypeDTO>  cardTypeDTOs= creditCardTypeService.findAll(signupDTO.getHid());
		//model - it is used to carry the data from controller to view
		model.addAttribute("cardTypeDTOs", cardTypeDTOs);
		return  "showCards";
	}

}
