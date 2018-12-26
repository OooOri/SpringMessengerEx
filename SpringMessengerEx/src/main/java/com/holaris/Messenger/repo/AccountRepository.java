package com.holaris.Messenger.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holaris.Messenger.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


	Account findByEmail(String email);

}
