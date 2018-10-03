package com.holaris.Messenger.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.repo.AccountRepository;
import com.holaris.Messenger.service.AccountService;

@Controller
public class RegisterController {

	@Autowired
	private AccountService accountService;
		
	
	@GetMapping("/register")
	public String registerAccount(Model model) {
		
		Account account = new Account();						
		model.addAttribute("account", account);		
		
		return "registerAccount";
	}
	
	@PostMapping("/register")
	public String registerAccountPost(@Valid Account account, BindingResult result, Model model) {
		
		Account accountExists = accountService.findAccountByEmail(account.getEmail());
		
		if(accountExists != null) {
			model.addAttribute("userExistMsg", "User already exists");		
			return "registerAccount";
		}
		
		if(result.hasErrors()) {
			return "registerAccount";
		} else {
			accountService.saveAccount(account);
			model.addAttribute("successMessage", "User has been reigstered successfully");
			model.addAttribute("account", new Account());			
		}		
			
		return "registerAccountSuccess";
	}
	
	
	@GetMapping("/aa")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.findAccountByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + account.getUsername() + " (" + account.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("aa");
        
        return modelAndView;
	}

}
