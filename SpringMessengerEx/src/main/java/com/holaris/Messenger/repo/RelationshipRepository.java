package com.holaris.Messenger.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holaris.Messenger.model.Relationship;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

}
