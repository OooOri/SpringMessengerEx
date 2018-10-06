package com.holaris.Messenger.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/login")
	public String login2() {
		return "login";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
}
