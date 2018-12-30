package com.holaris.Messenger.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.Relationship;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {


	boolean existsByFriendAccount(Optional<Account> findById);

	Relationship findByFriendAccount(Account account);

	
}
