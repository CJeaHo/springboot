package com.study.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.domain.Board;
import com.study.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	public BoardRepository boardRepository;

	public Page<Board> list(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board insert(Board board) {
		return boardRepository.save(board);
	}

	public Optional<Board> selectDetail(Long bNo) {
		return boardRepository.findById(bNo)
							  .map(board-> 
							  	 { board.setCount(board.getCount() + 1);
							  	   return boardRepository.save(board);
							  	 });// bno으로 상세보기하기 전에 기존 count에 +1을 하여 저장하여
									// board에 save한다
									// map 우선순위를 갖는다
	}

	public Board update(Board board) {
		Board reBoard = boardRepository.findById(board.getBNo()).get(); // 기존꺼를 가지고와서 영속성안에 넣어야한다
		reBoard.setTitle(board.getTitle()); // 기존의 제목을 넣고
		reBoard.setContent(board.getContent()); // 기존의 내용을 넣고
		
		return boardRepository.save(reBoard);
	}
}
