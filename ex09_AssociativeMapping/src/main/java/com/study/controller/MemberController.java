package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.domain.Member;
import com.study.service.MemberService;


@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/minsert")
	public String insert(Member member, Model model) {
		Member result = memberService.insert(member);
		model.addAttribute("member", result);
		return "member_insert";
	}

}
