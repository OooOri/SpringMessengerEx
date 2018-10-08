package com.holaris.Messenger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.AlarmMessage;
import com.holaris.Messenger.repo.AccountRepository;
import com.holaris.Messenger.repo.AlarmMessageRepository;
import com.holaris.Messenger.repo.RelationshipRepository;

@Service
public class RelationshipService {

	@Autowired
	private RelationshipRepository relationshipRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AlarmMessageRepository alarmMessageRepository;
	
	@Autowired
	private AccountService accountService;
	
	public AlarmMessage friendRequest(long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.findAccountByEmail(auth.getName());
		
		AlarmMessage alarmMessage = new AlarmMessage();
		alarmMessage.setAlarmContents("친구요청이 들어왔습니다");
		alarmMessage.setAlarmType("F");
		alarmMessage.setToAccount(accountRepository.findById(id).get());
		alarmMessage.setFromAccount(account.getId());
		alarmMessageRepository.save(alarmMessage);
		return alarmMessage;
		
	}
	
	public void friendRequestAccept(long id) {
		
		
	}

	
}
