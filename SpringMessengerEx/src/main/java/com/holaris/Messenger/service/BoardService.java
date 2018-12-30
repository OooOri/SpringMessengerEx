package com.holaris.Messenger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.Board;
import com.holaris.Messenger.repo.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private AccountService accountService;
	public void register(Board board) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.findAccountByEmail(auth.getName());
		board.setWriter(account.getEmail());
		boardRepository.save(board);
	}
	
	public Board read(long bno) {
		return boardRepository.findById(bno).get();
	}
	
	public void modify(Board board) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.findAccountByEmail(auth.getName());
		board.setWriter(account.getEmail());
		board.setViewcnt(boardRepository.getOne(board.getBno()).getViewcnt());
		board.setRegDate(boardRepository.getOne(board.getBno()).getRegDate());
		boardRepository.save(board);
		
	}
	
	public void remove(long bno) {
		boardRepository.deleteById(bno);
	}
	//나중에 페이지 구현 pageable로 전환
	public List<Board> listPage(){
		return boardRepository.findAll();
	}
	
	
	
}
