package com.kuebiko.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuebiko.controller.dto.CreditCardTypeDTO;
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


	@GetMapping("/showCards")
	public String showCards(Model model) {
		List<CreditCardTypeDTO>  cardTypeDTOs= creditCardTypeService.findAll();
		//model - it is used to carry the data from controller to view
		model.addAttribute("cardTypeDTOs", cardTypeDTOs);
		return  "showCards";
	}

}
