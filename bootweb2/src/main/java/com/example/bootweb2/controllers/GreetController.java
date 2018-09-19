package com.example.bootweb2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetController {
	@RequestMapping("/greet")
	public String greet() {
		return "greet";
	}
}