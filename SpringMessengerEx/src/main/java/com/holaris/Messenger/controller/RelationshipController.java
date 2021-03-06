package com.holaris.Messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.repo.AccountRepository;
import com.holaris.Messenger.repo.AlarmMessageRepository;
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
	/*	친구 요청을 2번이상 누른 경우. 이미 친구 신청을 했다고 경고창을 띄우거나 친구요청을 취소 하는 기능
	 * if(alarmMessageRepository.findByToAccount(accountRepository.findById(id).get())!=null) {
			System.out.println("이미 친구 신청을 했습니다.");
			return "redirect:/add_friend";
		}*/
		relationshipService.friendRequest(id);
			
		return "redirect:/mypage";
	}
	
	@GetMapping("/friendRequestAccept/{id}")
	public String friendRequestAccept(@PathVariable long id, Model model) {
		
	    relationshipService.friendRequestAccept(id);		
		return "redirect:/mypage";
	}
	
	
	@GetMapping("/friend/{id}")
	public String getAlarmMessage(@PathVariable long id, Model model) {
		Account account = accountRepository.getOne(id); 
		/*for(int i=0; i<account.getRelationship().size(); i++) {
			account.getRelationship().get(i).getFriendAccount().getId();
		}*/
		model.addAttribute("friend", account.getRelationship());
		
		return "friend";
	}
	
	
}
