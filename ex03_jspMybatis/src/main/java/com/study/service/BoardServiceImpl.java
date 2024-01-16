package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.dto.Board;
import com.study.repository.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	// 필요한 의존 객체의 빈을 알아서 자동 주입  
	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<Board> list() {
		return boardDao.list();
	}

	@Override
	public Board detailBoard(String no) {
		return boardDao.detailBoard(no);
	}

	@Override
	public int totalRecord() {
		return boardDao.totalRecord();
	}

	@Override
	public int insertBoard(Board board) {
		return boardDao.insertBoard(board);
	}

	@Override
	public int deleteBoard(String no) {
		return boardDao.deleteBoard(no);
	}

}