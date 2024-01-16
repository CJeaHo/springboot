package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.Board;
import com.study.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	// 홈
	@RequestMapping("/")
	public String root() throws Exception {
		return "redirect:list";
	}
	
	/*
	 요청 처리 후 응답페이지로 결과를 담는 방법
	 1. Model
	 	- 뷰로 전달하고자 하는 데이터를 맵형식(key, value)로 담을 수 있는 객체
	 	- requestScope이다
	 		단, setAttribute가 아닌 addAttribute메소드 이용
	 2. ModelaAndView
	 	- 뷰로 전달하고자 하는 데이터를 맵형식(key, value)로 담을 수 있는 객체 => Model
	 	- 응답뷰에 대한 정보를 담을 수 있는 공간 -> View
	 	
	 	ex)
	 	@RequestMapping("/list")
		public String list(ModelaAndView) {
			List<Board> list = boardService.list();
			mv.addObject("boardList", list) List로 객체를 담으므로 addObject, (key, value)형태로 가지고 간다
			mv.setViewName("/list");
			return mv;
		}
	 */
	//  목록 폼
	@RequestMapping("/list")
	public String list(Model model) {
//		List<Board> list = boardService.list();
//		model.addAttribute("list", list);
		model.addAttribute("list", boardService.list());
		model.addAttribute("totalRecord", boardService.totalRecord());
		return "list";
	}
	
	// 글쓰기 폼
	@GetMapping("/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	/*파라미터(요청시 전달값)를 받는 방법
	1. jsp에서 사용했던 HttpServletRequest를 이용하여 전달받기(기존 jsp/servlet)
	2. @RequestParam 어노테이션을 이용하는 방법
		속성
		value: uri에서 바인딩하게 될 값
		required(true|false): 필수적으로 값이 전달되게 할지 여부(true일때 값이 안들어오면 에러 발생)
		defaultValue: 값이 없을 때 기본적으로 사용할 값
		
		- @RequestParam("전달된 name(key)") 자료형 변수명(defaultValue를 안넣을 때 value는 생략)
			@RequestParam(value="key", defaultValue="", required=true) 자료형 변수명
			=> @RequestParam("key")
	3. 커맨드 객체 방식
		dto클래스 담는 방식
		요청시 전달값의 키(name)을 dto클래스에 담고자 하는 필드명을 작성
		
		스프링컨테이너가 해당 객체의 기본 생성자를 생성 후 setter메소드를 찾아서 전달된 값을 해당 필드에 내부적으로
		담아주는 원리
		- 기본 생성자가 무조건 있어야한다
		- setter메소드가 무조건 있어야한다
		- key이름이 dto의 필드명과 같아야한다
	*/
	
	// 글쓰기
	@PostMapping("write")
	public String write(Board board) {
//		System.out.println("제목:" + board.getTitle());
//		System.out.println("작성자:" + board.getWriter());
//		System.out.println("내용:" + board.getContent());
		
		boardService.insertBoard(board);
		
		return "redirect:/list"; // "/"없어도 된다
//		@RequestMapping("/list")을 재 호출하는 것
//		public String list() {
//			return "list";
//		}
	}
	
	// 상세보기
	@GetMapping("detail")
	public String detailBoard(HttpServletRequest request, Model model) { // <td><a href="detail?no=${blist.no }의 no을 받음
//		System.out.println(request.getParameter("no"));
		Board board = boardService.detailBoard(request.getParameter("no"));
		model.addAttribute("detailBoard", board);
		return "detailForm";
	}
	
	// 글 삭제
	@GetMapping("delete")
	public String delete(@RequestParam(value="no", defaultValue="1") String bno) { // onclick="location.href=delete?no=${blist.no}'">삭제의 no를 받음
		System.out.println(bno);
		boardService.deleteBoard(bno);
		return "redirect:/list";
	}
}
