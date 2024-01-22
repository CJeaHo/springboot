package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.domain.Board;
import com.study.domain.Member;
import com.study.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@PostMapping("/binsert")
	public String insert(Board board, Model model) {
		// writer: user01이 게시글을 썼다 라는 가정
		Member m = new Member();
		m.setId("user01"); // user01이라는 id를 member객체의 id변수에 넣어주고
		board.setMember(m); // 그 user01을 board객체의 member에 넣어줌
		
		// board.setMember("user01"); 로 하면 안된다 왜냐면 member객체와 연결되어 넣어야하기 때문
		
		Board result = boardService.insert(board);
		model.addAttribute("boards", result);
		
		/*
		 *  user01의 값을 board를 통해 꺼내고 싶다면
		 *  member의 id에 넣고board의 member에 넣은 것이기에 거꾸로 뒤집어서 계속해서 get해야한다
		 *  result.getMember().getId()
		 */
		System.out.println(result.getMember().getId());
		return "board_insert";
	}
}
