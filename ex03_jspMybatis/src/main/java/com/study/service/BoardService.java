package com.study.service;

import java.util.List;

import com.study.dto.Board;

public interface BoardService {
	List<Board> list(); //List는 객체 arrayList는 자료형
	Board detailBoard(String no); //request.getParameter로 받으면 String이므로 적어도된다(하지만 나중엔 형변환해야할듯)
	int totalRecord();
	int insertBoard(Board board);
	int deleteBoard(String no);
	
}
