package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	public ReplyRepository replyRepository; 
	
//	public void Reply(Long bNo) {
//		replyRepository.findAllByRefbnoOrderByRnoDesc(bNo);
//	}

}
