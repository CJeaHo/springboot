package com.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.dto.Board;

//sql문(xml)과 메소드를 연결하고 결과 값을 정의해놓은 타입으로 매핑시켜주는 것
@Mapper
public interface BoardDao {
	List<Board> list(); //arrayList랑 차이가 있던가
	Board detailBoard(String no); //request.getParameter로 받으면 String이므로 적어도된다(하지만 나중엔 형변환해야할듯)
	int totalRecord();
	int insertBoard(Board board);
	int deleteBoard(String no);
}
