package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.domain.Member;
import com.study.service.MemberService;


@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "menu";
	}
	
	@GetMapping("/selectByNameLike")
	public String selectByNameLike(@RequestParam("name") String name, 
									@RequestParam("page") int page, 
									Model model) {
		name = "%" + name + "%";
		Sort sort = Sort.by(Sort.Order.desc("name"));

		// JPA에서 paging의 1페이지는 0부터 시작
		page= page - 1;
		
		// 1페이지당 10개씩 현재페이지에 정렬되어 가지고오는 것을 pageable에 담는다
		Pageable pageable = PageRequest.ofSize(10).withPage(page).withSort(sort); // 1페이지당 몇개를 보여줄것인가
	    
		Page<Member> result = memberService.selectByNameLike(name, pageable);
		List<Member> content = result.getContent(); // 실제 객체가 담긴 list
		long totalElements = result.getTotalElements(); // 총 content수
		int totalPages = result.getTotalPages(); // 총 페이지수
		int size = result.getSize(); // ofSize를 받아오는것 즉, 1페이지당 10개를 보여주겠다
		int pageNumber = result.getNumber() + 1; // 현재 페이지(0부터 시작하는데 1페이지부터 보여줘야한다)
		int numberOfElements = result.getNumberOfElements(); // 현재페이지에 들어오는 content개수 (10으로 나눈 그 나머지도 들어와야한다)
		
		model.addAttribute("contents", content);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numberOfElements", numberOfElements);
		
	    return "select_list";
	}

}
