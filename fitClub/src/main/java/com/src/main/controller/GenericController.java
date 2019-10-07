package com.src.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenericController {
 
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
 
}
