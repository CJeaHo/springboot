package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Board;
import com.study.repository.BoardRepository;


@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public Board insert(Board board) {
		return boardRepository.save(board);
	}


	
}
