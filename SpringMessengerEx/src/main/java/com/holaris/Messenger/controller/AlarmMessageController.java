package com.holaris.Messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.AlarmMessage;
import com.holaris.Messenger.repo.AccountRepository;

@Controller
public class AlarmMessageController {

	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/alarmMessage/{id}")
	public String getAlarmMessage(@PathVariable long id, Model model) {
		Account account = accountRepository.findById(id).get(); 
		if(account.getAlarmMessage() != null) {
			model.addAttribute("alarmMessage", account.getAlarmMessage());
		}
		
		return "alarmMessage";
	}
}
