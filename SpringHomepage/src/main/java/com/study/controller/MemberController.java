package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.domain.Member;
import com.study.service.MemberService;

@Controller
@SessionAttributes({"loginMember"}) // 로그인은 유지되어야하는데 model은 일시적이여서 계속 유지가 안되므로 세션에 넣어 처리
public class MemberController {

	@Autowired
	public MemberService memberService;
	
	@Autowired
	public PasswordEncoder pEncoder;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "index";
	}
	
	@RequestMapping("index")
	public String index() throws Exception {
		return "index";
	}
	
	@GetMapping("registerForm.ac")
	public String registerForm() {
		return "member/registerForm";
	}
	
	@GetMapping("idCheck.aj")
	@ResponseBody
	public boolean idCheck(@RequestParam("checkId") String memberId) {
		return memberService.idCheck(memberId);
	}
	
	@PostMapping("register.ac")
	public String register(Member member) {
		// encode: 기본으로 제공해주는 메소드로 패스워드를 인코딩 시켜줌
		String realpw = pEncoder.encode(member.getPassword()); 
		member.setPassword(realpw);
		memberService.register(member);
		return "redirect:/";
	}
	
	@PostMapping("login.ac")
	public String login(Member member, Model model) { 
		Member loginUser = memberService.login(member);
		// loginUser가 null이 아니고, 사용자가 입력한 패스워드, 데이터베이스에서 가지고온 패스워드를 비교해서 맞는지
		if(loginUser != null && pEncoder.matches(member.getPassword(), loginUser.getPassword())) {
			model.addAttribute("loginMember",loginUser);
		}
		return "redirect:/";
	}
	
	@GetMapping("logout.ac")
	public String logout(SessionStatus status) { // 세션 상태
		if(!status.isComplete()) // 세션상태가 완료되지 않았다(로그인상태)
			status.setComplete(); // 세션상태 완료시키겠다(로그아웃)
		return "redirect:/";
	}
}
