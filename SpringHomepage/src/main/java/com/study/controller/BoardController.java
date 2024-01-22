package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.study.domain.Board;
import com.study.domain.Member;
import com.study.service.BoardService;
import com.study.service.ReplyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	public BoardService boardService;
	
	@Autowired
	public ReplyService replyService;
	
	private Member loginMember; // 현재 controller에서 모두 사용하기 위해 인스턴스 변수로 선언

	@GetMapping("list.ac")
	public String list(@RequestParam(value="nowPage", defaultValue = "0") int nowPage, 
									 HttpSession session, Model model) { // nowPage(현재 페이지)를 기본값 0으로
				
		if(session.getAttribute("loginMember") != null) { // 로그인할 때 세션에 그 그 정보를 loginMember로 저장해 놨음
			loginMember = (Member) session.getAttribute("loginMember");
			model.addAttribute("loginMember",loginMember);
		}
										// pageRequest.of(현재페이지, 페이지당 개수[, sort])라는 메소드를 사용하면 한번에 만들어짐
		model.addAttribute("boardPage", boardService.list(PageRequest.of(nowPage, 10)));
		model.addAttribute("nowPage", nowPage);
		return "board/list";
	}
	
	@GetMapping("writeForm")
	public String writeForm(Model model) {
		model.addAttribute("loginMember",loginMember);
		return "board/writeForm";
	}
	
	@PostMapping("write.ac")
	// @SessionAttribute("loginMember")는 현재 메소드에서만 사용 가능
	public String write(Board board /*@SessionAttribute("loginMember") Member member*/) {
		
		board.setWriter(loginMember.getId()); // 세션에 저장된 로그인된 멤버의 아이디를 writer에 저장
		boardService.insert(board);
		return "redirect:list.ac";
	}
	
	@GetMapping("detail.ac")
	public String detail(@RequestParam("bNo") Long bNo, Model model) {
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		}
		
//		replyService.Reply(bNo);
		Board board = boardService.selectDetail(bNo).get();
		model.addAttribute("board", board);
		return "board/detail";
	}
	
	@PostMapping("update.ac")
	public String update(Board board) {
		 boardService.update(board);
		return "redirect:list.ac";
	}
}
















