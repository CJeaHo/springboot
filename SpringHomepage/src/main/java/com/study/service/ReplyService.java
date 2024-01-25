package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Reply;
import com.study.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	public ReplyRepository replyRepository;
	
	// 게시글 댓글 조회
    public List<Reply> replyList(Long bNo) {
        return replyRepository.findAllByRefBnoOrderByRnoDesc(bNo);
    }

    // 게시글 댓글 입력
	public Reply reply(Reply reply) {
		return replyRepository.save(reply);
	} 
}
