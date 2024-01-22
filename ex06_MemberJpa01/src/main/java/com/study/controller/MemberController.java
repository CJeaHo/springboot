package com.study.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/") // 서버를 키면 바로가는 것
	public String root() throws Exception {
		return "menu";
	}
	
	@GetMapping("/insert") // 값을 하나만 가지고 올거면 @RequestParam을 쓰는게 편하다
	public String insert(@RequestParam("username") String username, Model model) {

//		Member member = new Member();
//		member.setUsername(username);
//		member.setCreateDate(LocalDate.now()
		
// 		나에게 맞는 생성자를 만드는 용도로 builder 사용, 가독성이 좋다
		Member member = Member.builder()
						.username(username)
						.createDate(LocalDate.now())
						.build();
		Member result = memberService.insert(member);
		model.addAttribute("m",result);
		return "insert";
	}
	
	@GetMapping("/select")
	public String select(@RequestParam("id") Long id, Model model) {
		
		Optional<Member> result = memberService.select(id);
		
		// Optional<Member>는 wrapping되는 것들이기에 값을 가지고 오려면 벗겨줘야한다 =>.get()
		// result.get().getUsername();
		// result.get().getId();
		
		// Member member = memberService.select(id).get(); // 미리 벗겨놓고 변수에 넣어줘도 된다
		
		// isPresent(): 값이 들어있는지 여부확인
		if(result.isPresent()) {
			model.addAttribute("me", result.get()); // result.get() => Optional<Member>의 wrapping을 벗겨주는 것이므로 Member형으로 넘겨주는 것
		//			model.addAttribute("me", member);
		} else {
			model.addAttribute("me", null);
		}
		return "select";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		model.addAttribute("meAll", memberService.selectAll());
		return "selectAll";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		memberService.delete(id);
		return "redirect:selectAll";
	}
	
	@GetMapping("/update") // 2개 이상의 값을 받아올거면 entity로 가는게 편하다
	public String update(Member member, Model model) {
		
		model.addAttribute("mem", memberService.update(member));
		return "update";
	}
}
