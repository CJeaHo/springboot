package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	// boot dashboard에서 지구본모양을 클릭하면 1번이 실행되고 주소창에 /test를 입력하면 2번이 실행

	// requestMapping: get이든 post든 다 받음
	//1.
	@RequestMapping("/")
	public @ResponseBody String root() { // responsebody: return값을 순수 String으로 실행
		return "jsp로 실행"; // 원래 /WEB-INF/views/jsp로 실행.jsp인데 responsebody로 하면 그냥 jsp로 실행이라는 문구 출력으로 바뀜
	}
	
	//2.
	@RequestMapping("/test1") 
	public String test1() {
		return "test1"; ///WEB-INF/views/에 있는 test.jsp 로 이동 = requestDispatcher~
	}
	
	//3.
	@RequestMapping("/test2")
	public String test2() {
		return "sub/test2"; // /WEB-INF/views/ + sub/test2 + .jsp
	}
	
}
