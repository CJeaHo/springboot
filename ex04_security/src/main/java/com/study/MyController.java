package com.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@RequestMapping("/")
	public @ResponseBody String root() { // return값을 페이지가 아닌 그냥 문구 그자체로
		return "Security Welcome"; 
	}
	
	@RequestMapping("guest/welcome")
	public String welcome3() {
		return "guest/welcome3";
	}
	
	@RequestMapping("member/welcome")
	public String welcome2() {
		return "member/welcome2";
	}
	@RequestMapping("admin/welcome")
	public String welcome1() {
		return "admin/welcome1";
	}
	
	// security자체가 get으로 받음
	@GetMapping("/loginForm")
	public String loginForm() {
		return "security/loginForm";
	}
}
