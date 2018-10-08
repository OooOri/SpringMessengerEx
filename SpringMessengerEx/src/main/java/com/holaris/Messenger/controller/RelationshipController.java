package com.holaris.Messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.holaris.Messenger.model.AlarmMessage;
import com.holaris.Messenger.repo.AccountRepository;
import com.holaris.Messenger.repo.AlarmMessageRepository;
import com.holaris.Messenger.repo.RelationshipRepository;
import com.holaris.Messenger.service.AccountService;
import com.holaris.Messenger.service.RelationshipService;



@Controller
public class RelationshipController {
	
	@Autowired
	private RelationshipService relationshipService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AlarmMessageRepository alarmMessageRepository;
	
	@GetMapping("/requestFriend/{id}")
	public String requestFriend(@PathVariable long id, Model model) {
	/*	if(alarmMessageRepository.findByToAccount(accountRepository.findById(id).get())!=null) {
			System.out.println("추가할 수 없습니다.");
			return "redirect:/add_friend";
		}*/
		relationshipService.friendRequest(id);
			
		return "redirect:/add_friend";
	}
	
	@GetMapping("/friendRequestAccept/{id}")
	public String friendRequestAccept(@PathVariable long id, Model model) {
	    
			
		return "redirect:/alarmMessage";
	}
}
