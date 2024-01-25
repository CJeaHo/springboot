package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.domain.Reply;
import com.study.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	public ReplyService replyService;
	
	@PostMapping("reply")
	public @ResponseBody String reply(@RequestParam("boardNo") Long boardNo,
            						  @RequestParam("replyContent") String replyContent,
            						  @RequestParam("loginMemberId") String loginMemberId) {
		
		Reply reply = new Reply();
		reply.setRefBno(boardNo);
		reply.setRContent(replyContent);
		reply.setRWriter(loginMemberId);
		
		replyService.reply(reply);
		
		return "댓글이 성공적으로 등록되었습니다.";
	}
}
