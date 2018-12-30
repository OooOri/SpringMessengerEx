package com.holaris.Messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.service.AccountService;

@Controller
public class SearchController {

	@Autowired
	AccountService accountService;
	
	@GetMapping("/account_search")
	public String accountSearch(@RequestParam(value="search", required=false)String searchText, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.findAccountByEmail(auth.getName());
		if (searchText == null) {
			return "account_search";
		}
		model.addAttribute("userList", accountService.searchAccounts(searchText));
		model.addAttribute("currentUser", account.getEmail());
		return "account_search";
		
	}
}
