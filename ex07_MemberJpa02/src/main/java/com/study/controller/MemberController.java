package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.service.MemberService;


@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "menu";
	}
	
	@GetMapping("/insert")
	public String insert() {
		memberService.insert();
		return "insert";
	}
	
	@GetMapping("/selectAll")
	public String select(Model model) {
		model.addAttribute("members", memberService.select()); 
		return "select_All";
	}
	@GetMapping("/selectById")
	public String selectById(@RequestParam("id") Long id, Model model) {
		model.addAttribute("m", memberService.selectById(id).get());
		return "select_Id";
	}

	@GetMapping("/selectByEmail")
	public String selectByEmail(@RequestParam("email") String email, Model model) {
		model.addAttribute("m", memberService.selectByEmail(email).get());
		return "select_Email";
	}
	
	@GetMapping("/selectByName")
	public String selectByName(@RequestParam("name") String name, Model model) {
		model.addAttribute("m", memberService.selectByName(name).get());
		return "select_Name";
	}
	
	@GetMapping("/selectByNameLike")
	public String selectByNameLike(@RequestParam("name") String name, Model model) {
		name = "%" + name + "%";
	    model.addAttribute("m", memberService.selectByNameLike(name));
	    return "select_NameLike";
	}
	
	@GetMapping("/selectByNameLikeDesc") // sort없이 내림차순
	public String selectByNameLikeDesc(@RequestParam("name") String name, Model model) {
		name = "%" + name + "%";
	    model.addAttribute("m", memberService.selectByNameLikeDesc(name));
	    return "select_NameLike_Desc";
	}
	
	@GetMapping("/selectByNameLikeSort") // sort를 사용하여 내림차순
	public String selectByNameLikeSort(@RequestParam("name") String name, Model model) {
		name = "%" + name + "%";
		Sort sort = Sort.by(Sort.Order.desc("name"));
	    model.addAttribute("m", memberService.selectByNameLikeSort(name, sort));
	    return "select_NameLike_Sort";
	}

}
