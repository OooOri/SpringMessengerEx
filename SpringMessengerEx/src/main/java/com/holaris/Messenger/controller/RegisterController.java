package com.holaris.Messenger.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.repo.AccountRepository;
import com.holaris.Messenger.service.AccountService;

@Controller
public class RegisterController {

	@Autowired
	private AccountService accountService;
		
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/register")
	public String registerAccount(Model model) {
		
		Account account = new Account();						
		model.addAttribute("account", account);		
		
		return "registerAccount";
	}
	
	@PostMapping("/register")
	public String registerAccountPost(@Valid Account account, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "registerAccount";
		}
		
		if(accountRepository.existsByEmail(account.getEmail())) {
			model.addAttribute("usernameMsg", "Username already exists");
			
			return "registerAccount";
		}
		
		accountRepository.save(accountService.createAccount(account.getUsername(), account.getPassword(), account.getEmail()));
			
		return "registerAccountSuccess";
	}
}
