package com.holaris.Messenger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holaris.Messenger.model.Board;
import com.holaris.Messenger.repo.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public void register(Board board) {
		boardRepository.save(board);
	}
	
	public Board read(long bno) {
		return boardRepository.findById(bno).get();
	}
	
	public void modify(Board board) {
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
