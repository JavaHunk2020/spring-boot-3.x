package com.kuebiko.controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardController {

	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = "acb";
		String sortedChars1 = Stream.of(str1.split("")).sorted().collect(Collectors.joining());
		String sortedChars2 = Stream.of(str2.split("")).sorted().collect(Collectors.joining());
		if (sortedChars1.equals(sortedChars2)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	@GetMapping("/showCards")
	public String showCards() {

		return "showCards";
	}

}
