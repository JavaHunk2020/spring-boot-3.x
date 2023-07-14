package com.kuebiko.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.controller.dto.CreditCardTypeDTO;
import com.kuebiko.service.CreditCardTypeService;

@RestController
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
@RequestMapping("/v1/creditcards")
public class MasterCreditCardRestController {
	
	@Autowired
	private CreditCardTypeService creditCardTypeService;
	
	
	@GetMapping("/available/{sid}")
	public List<CreditCardTypeDTO>findAvailableCards(@PathVariable int sid) {
		return creditCardTypeService.findAll(sid);
	}
	
	@GetMapping("/image")
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



}
