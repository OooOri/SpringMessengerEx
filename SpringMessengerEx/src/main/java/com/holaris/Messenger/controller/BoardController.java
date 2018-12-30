package com.holaris.Messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.holaris.Messenger.model.Account;
import com.holaris.Messenger.model.Board;
import com.holaris.Messenger.repo.BoardRepository;
import com.holaris.Messenger.service.AccountService;
import com.holaris.Messenger.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private AccountService accountService;
	
	
	@GetMapping("/board")
	public String listAll(Model model, @PageableDefault(sort = { "bno" }, direction = Direction.DESC, size = 10) Pageable pageable) {
		Page<Board> boardPage = boardRepository.findAll(pageable);
		
		/*System.out.println(boardPage.getNumber()); //현재 페이지 번호
		System.out.println(boardPage.getNumberOfElements()); // 화면에 보이는 게시물 수
		System.out.println(boardPage.getSize()); // 화면에 몇 개 나와야 되는지 정한 수
		System.out.println(boardPage.getTotalElements()); // 총 게시물 수
		System.out.println(boardPage.getTotalPages()); // 총 게시판 번호
		//System.out.println(boardPage.getContent());
		//System.out.println(boardPage.getPageable());
		//System.out.println(boardPage.getSort());
		System.out.println(boardPage.nextPageable());//다음 페이지 번호와 게시물 정보
		System.out.println(boardPage.previousPageable());// 이전 페이지 번호와 게시물 정보
		System.out.println(boardPage.hasNext()); 
		System.out.println(boardPage.hasPrevious());
		int displayPageNum = 10;
		int endPage = (int) (Math.ceil(boardPage.getNumber()/(double)displayPageNum)*displayPageNum);
		int statPage = (endPage-displayPageNum)+1;*/
		
		model.addAttribute("boardPage", boardPage);		
		
		/*List<Board> boardPage = boardRepository.findAll();
		model.addAttribute("boardPage", boardPage);	*/
		
		return "board";
	}
	
	@GetMapping("/board/register")
	public String registerGET(Model model) {
//		System.out.println(boardRepository.findFirstByOrderByBnoDesc().getBno());
		Board board = new Board();
//		board.setBno(boardRepository.findFirstByOrderByBnoDesc().getBno()+1);
		model.addAttribute("board", board);
	/*	for (int i = 1; i <= 20; i++) {
			Board board = new Board();
			board.setWriter("account " + i);
			board.setContents("hi " + i);
			boardRepository.save(board);
		}
		
		Sort sort = new Sort.Order(Direction.ASC, "lastName");
		Page pageable = new PageRequest(0, 5, sort);
		List<Board> boardPage = boardRepository.findBySalaryGreaterThan(new Long(10000), pageable);
	
		List<Board> boardPage = boardRepository.findAll();
		
		model.addAttribute("boardPage", boardPage);	*/	
		
		return "/board/register";
		
	}
	
	@PostMapping("/board/register")
	public String registerPost(Board board, Model model) {
		boardService.register(board);
		
		return "redirect:/board";
	}
	
	@GetMapping("/board/read")
	public void read(@RequestParam("bno") long bno, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.findAccountByEmail(auth.getName());
		
		boardRepository.getOne(bno).setViewcnt(boardRepository.getOne(bno).getViewcnt()+1);
		boardRepository.save(boardRepository.getOne(bno));
		
		model.addAttribute("board",boardService.read(bno));
		model.addAttribute("currentUser", account.getEmail());
	}
	
	@PostMapping("/board/remove")
	public String remove(@RequestParam("bno") long bno, RedirectAttributes rttr) {
		boardService.remove(bno);
		rttr.addFlashAttribute("msg", "SUCCESS");
			
		return "redirect:/board";
	}
	
	@GetMapping("/board/modify")
	public void modifyGET(int bno, Model model) {
		model.addAttribute("board", boardService.read(bno));
		
	}
	
	@PostMapping("/board/modify")
	public String modifyPOST(Board board, RedirectAttributes rttr) {
		boardService.modify(board);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board";
	}
	
}
