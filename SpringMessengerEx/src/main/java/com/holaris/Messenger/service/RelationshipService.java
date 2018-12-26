package com.holaris.Messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.AlarmMessage;
import com.holaris.Messenger.model.Relationship;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account currentAccount = accountService.findAccountByEmail(auth.getName());
		/*	System.out.println(accountRepository.findById(id).get().getId());
			
			System.out.println(relationshipRepository.existsByFriendAccount(accountRepository.findById(id)));
			System.out.println(!currentAccount.getRelationship().isEmpty());
			System.out.println(relationshipRepository.existsByFriendAccount(accountRepository.findById(id)) && !currentAccount.getRelationship().isEmpty());
			System.out.println(currentAccount.getRelationship().contains(relationshipRepository.findByFriendAccount(accountRepository.findById(id).get())));
		
//		set을 쓰면 중복검사 필요 없지 않을까
		if(relationshipRepository.existsByFriendAccount(accountRepository.findById(id)) && !currentAccount.getRelationship().isEmpty()) {
//			for(Relationship relationship : currentAccount.getRelationship()) {
				if(currentAccount.getRelationship().contains(relationshipRepository.findByFriendAccount(accountRepository.findById(id).get())) == accountRepository.findById(id).get().get){
					System.out.println("이미 친구 입니다.");
					return;
				}
//			}
		}*/
		
		
		Relationship relationship = new Relationship();
		relationship.setFriendAccount(accountRepository.findById(id).get());
		relationship.setFriend(true);
		relationshipRepository.save(relationship);
		
		currentAccount.getRelationship().add(relationship);
		
		Relationship relationship2 = new Relationship();
		relationship2.setFriendAccount(currentAccount);
		relationship2.setFriend(true);
		relationshipRepository.save(relationship2);
		
		accountRepository.findById(id).get().getRelationship().add(relationship2);
		
/*		relationshipRepository.flush(); //두번 누를 때도 위의 조건문에 false로 걸리고 세번 눌러서 true로 바뀌는데 그때는 2개의 결과값을 리턴하므로 오류가 NonUniqueResultException발생하므로 강제로 db저장
		accountRepository.flush();*/
	}

	
}
