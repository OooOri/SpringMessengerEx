package com.holaris.Messenger.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.holaris.Messenger.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
