package com.holaris.Messenger.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holaris.Messenger.model.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
	Board findFirstByOrderByBnoDesc();
}
