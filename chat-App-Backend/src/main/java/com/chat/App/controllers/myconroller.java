package com.chat.App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myconroller {
	
	@GetMapping("/")
    public String show() {
    	return "index";
    }
	
}
