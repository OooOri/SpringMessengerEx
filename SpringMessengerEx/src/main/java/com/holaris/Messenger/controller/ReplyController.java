package com.holaris.Messenger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.Reply;
import com.holaris.Messenger.repo.BoardRepository;
import com.holaris.Messenger.repo.ReplyRepository;
import com.holaris.Messenger.service.AccountService;

@RestController
public class ReplyController {

	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private AccountService accountService;
	@PostMapping("/replies")
	public ResponseEntity<String> register(@RequestBody Reply reply){
		//ResponseEntity<String> entity = null;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Account account = accountService.findAccountByEmail(auth.getName());
			boardRepository.getOne(reply.getBno()).getReply().add(reply);
			reply.setConnected_bno(boardRepository.getOne(reply.getBno()));
			reply.setReplyer(account.getEmail());
			replyRepository.save(reply);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	
	}
	
	@GetMapping("/replies/all/{bno}")
	public ResponseEntity<List<Reply>> list(@PathVariable("bno") long bno){
		
		//ResponseEntity<List<Reply>> entity = null;
		
		try {
			return new ResponseEntity<>(boardRepository.getOne(bno).getReply(),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/replies/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rno") long rno, @RequestBody Reply reply){
		ResponseEntity<String> entity = null;
		
		try {
			replyRepository.getOne(rno).setReplytext(reply.getReplytext());
			replyRepository.save(replyRepository.getOne(rno));
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping("/replies/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") long rno){	
		try {
			boardRepository.getOne(replyRepository.getOne(rno).getConnected_bno().getBno()).getReply().remove(replyRepository.getOne(rno));
			replyRepository.getOne(rno).setConnected_bno(null);
			replyRepository.deleteById(rno);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
