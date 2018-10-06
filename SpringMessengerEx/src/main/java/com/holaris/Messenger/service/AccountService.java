package com.holaris.Messenger.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.repo.AccountDAO;
import com.holaris.Messenger.repo.AccountRepository;


@Service
public class AccountService{

	@Autowired
    private AccountRepository accountRepository;
	@Autowired
    private AccountDAO accountDAO;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public AccountService(AccountRepository accountRepository,
      		BCryptPasswordEncoder bCryptPasswordEncoder) {
    	this.accountRepository = accountRepository;
    	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    public Account findAccountByEmail(String email) {
    	return accountRepository.findByEmail(email);
    }
     
    
    public void saveAccount(Account account) {
    	account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
    	account.setEnabled(true);
    	account.setAuthority("USER");
    	accountRepository.save(account);
    }
    
    public List<Account> searchAccounts(String searchText) {
		
		List<Account> userList = accountDAO.searchAccounts(searchText);
		
		return userList;
	}

}
