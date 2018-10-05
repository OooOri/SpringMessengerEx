package com.holaris.Messenger.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.holaris.Messenger.model.Account;

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
