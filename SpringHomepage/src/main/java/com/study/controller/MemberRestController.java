package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.Member;
import com.study.dto.ResponseDto;
import com.study.dto.UserDto;
import com.study.service.MemberRestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest")
@Slf4j // log.info를 하게 해줌
public class MemberRestController {

	@Autowired
	MemberRestService memberRestService;
	
	@GetMapping("/test") // http://localhost:8080/rest/test를 입력할 것
	public String test() {
		// 둘다 console창에 뜸
		System.out.println("test1");
		log.info("test2");
		return "test";
	}
	
	@GetMapping("/user") // 주소창에 /rest/user를 입력할 것
	public String getMember(@RequestParam(value="empNo", defaultValue="0") String empNo) { // http://localhost:8080/rest/user?empNo

		log.info("empNo : {}", empNo);
		System.out.printf("empNo : %s",empNo);
		return "ok: " + empNo;
	}
	
	@GetMapping("/user/{inputId}") // 주소창에 /rest/user를 입력할 것
	public String getMemberId(@PathVariable("inputId") String memberId) { // http://localhost:8080/rest/user/{inputId}

		log.info("memberId : {}", memberId);
		System.out.printf("memberId : %s",memberId);
		return "ok: " + memberId;
	}
	
	// post로 보내면 http프로토콜 안에 넣어 보내준다
	// http프로토콜 안에 넣어줄 수 없기 때문에 test용으로 실행해봐야함
	/*
	 * 1. postman 사용
	 * 2. webmvc 사용(mvnRepository에서 검색해 properties에 설정하여 사용 SpringDoc OpenAPI Starter WebMVC UI 이거임)
	 * 		사용방법: 웹브라우저에서 urI맨 뒤에 swagger-ui.html을 붙임
	 * */
	@PostMapping("/item")
	public String saveItem(@RequestBody String item) { // http://localhost:8080/swagger-ui.html 입력하면
														// http://localhost:8080/rest/item 출력됨
		log.info("item : {}", item);
		System.out.printf("item : %s", item);
		return "ok: " + item;
	}
	
	/*
	 * json으로 아래와 같은 형태로 받고 싶을 때
	 * 먼저 dto를 만들어 객체에 담아 return
	 * {
	 * 	"id" : "user01",
	 *  "name": "홍길동"
	 * }*/
	
	@PostMapping("/userDto")
	public UserDto saveUserDto(@RequestBody UserDto userDto) {
		Member m = memberRestService.saveUserDto(userDto);
		UserDto reDto = new UserDto(m.getId(), m.getName()); // 생성자의 인자(String id, String name)
		// = UserDto reDto = new UserDto(m); 생성자의 인자(Member m)
		// = UserDto reDto = new UserDto(memberRestService.saveUserDto(userDto)); 

		System.out.println(reDto);
		return reDto;
	}
	
	@PostMapping("/responseDto")
	public ResponseDto saveResponseDto(@RequestBody UserDto userDto) {
		Member m = memberRestService.saveUserDto(userDto);
		ResponseDto responseDto = new ResponseDto();
		if(m.getId() != null) {
			responseDto.setMessage("yeah");
			return responseDto;
		}
		responseDto.setMessage("nope");
		
		return responseDto;
	}
	
	/*@GetMapping("/userDto")
	public UserDto getUserDto(@RequestParam("id") String id) { // http://localhost:8080/rest/userDto?id=
		return memberRestService.getUserById(id);
	}*/
	
	@GetMapping("/userDto/{id}")
	public UserDto getUserDto(@PathVariable("id") String id) { // http://localhost:8080/rest/userDto/{id}
		return memberRestService.getUserById(id);
	}
	
	@GetMapping("/userAll")
	public List<Member> getUserAll() {
		return memberRestService.getUserAll();
	}
	
	@GetMapping("/userDtoAll")
	public List<UserDto> getUserDtoAll() {
		return memberRestService.getUserDtoAll();
	}
	
	
}
