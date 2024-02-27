package com.study.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.Menu;
import com.study.domain.Taste;
import com.study.domain.Type;
import com.study.service.MenuService;


@RestController
@RequestMapping("/menu")
public class MenuRestController {
	
	@Autowired
	MenuService menuService;

	@GetMapping
	public List<Menu> menuAllList() {
		return menuService.menuAllList();
	}
	// 사용자가 db에 있는 id를 넣었을 때는 정상 조회
	// db에 없는 id를 넣었을 때는 오류 500 (서버측 오류)
	/*
	 지금까지 상태코드를 지정하기 위해 HttpServletResponse의 setStatus()와 sendError()를 사용
	 문제점은 에러 시, json이 아닌 html 결과를 응답함
	 정상, 비정상 모두 json 응답을 위해 ResponseEntity 사용.
	 
	 restful하게 만들 때는 요구한 자원이 없을 때 404오류 반환 (클라이언트 측 오류<id가 없는 것을 넣었기 때문>)
	 그래서 null과 null이 아닐 때를 나누어 처리가 필요
	 
	 * responseEntity: 결과 데이터와 http상태 코드와 오류 코드까지 직접 제어할 수 있는 클래스
	 					(HttpRequest에 대한 응답 데이터가 포함되어 있음)
	 - status: 응답에 대한 상태 코드
	 - header: header값 (요청/응답)에 대한 요구사항
	 - body: 메시지 body에 작성될 내용(요구사항의 내용)
	 
	 * ResponseEntity 사용법
	 - status와 body를 이용
	   ResponseEntity.status(상태코드).body(객체)
	   
	   + 상태코드 HttpStatus에 정의된 값 이용
	   httpStatus.html(spring framework)에 접속하면 상태 코드를 알 수 있다
	 	
	   + 상태코드 OK와 body를 한번에 사용
	   ResponseEntity.ok(menu)
	   
	   + body가 없을 때, build() 사용
	   ResponseEntity.status(HttpStatus.NOT_FOUND).build(); build: 생성자가 없을 때 대신 사용
	   
	   + body가 없고, status대신 사용하는 메서드
	   noContent(): 204, 안에 내용이 없다
	   badRequest(): 400
	   notFound(): 404
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Menu> oneMenu(@PathVariable("id") Long id) {
		Optional<Menu> menu = menuService.oneMenu(id);
		
		if(menu.isPresent()) {
			// return ResponseEntity.ok().body(menu.get()); // ok는 200번
			return ResponseEntity.ok(menu.get()); // 위 코드와 동일
		} else {
			return ResponseEntity.notFound().build(); // 404 
		}
	} 
	
	@GetMapping("/type/{type}")
	public List<Menu> menuType(@PathVariable("type") Type type) {
		System.out.println(type);
		return menuService.menuType(type);
	}
	
	@GetMapping("/type/{type}/taste/{taste}")
	public List<Menu> menuTypeTaste(@PathVariable("type") Type type, @PathVariable("taste") Taste taste) {
		
		return menuService.menuTypeTaste(type, taste);
	}
	
	/*@PostMapping
	public Menu saveMenu (Menu menu) {
		return menuService.saveMenu(menu);
	}*/
	
	@PostMapping
	public ResponseEntity<?> insertMenu(@RequestBody Menu menu) {
		System.out.println(menu);
		Menu reMenu = menuService.insertMenu(menu);
		return ResponseEntity.created(URI.create("/menu/" + reMenu.getId())).build(); // path("/menu/{id})  
	}
	
	@PutMapping
	public ResponseEntity<?> updateMenu(@RequestBody Menu menu) {
		Menu reMenu = menuService.updateMenu(menu);
		return ResponseEntity.ok(reMenu); // ok: 상태코드 200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable("id") Long id) {
		menuService.deleteMenu(id);
		return ResponseEntity.noContent().build(); // noContent(): 상태코드 204 => 반환되는 내용이 하나도 없다
	}
		
}
