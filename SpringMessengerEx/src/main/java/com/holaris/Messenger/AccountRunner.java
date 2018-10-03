/*package com.holaris.Messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.service.AccountService;

@Component
public class AccountRunner implements ApplicationRunner {

	@Autowired
	AccountService accountService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account keesun = accountService.createAccount("keesun", "1234");
		System.out.println(keesun.getUsername() + " password: " + keesun.getPassword());
		
	}
	
	
}*/
