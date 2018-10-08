package com.holaris.Messenger.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.AlarmMessage;

public interface AlarmMessageRepository extends JpaRepository<AlarmMessage, Long>{

	Account findByToAccount(Account account);

}
