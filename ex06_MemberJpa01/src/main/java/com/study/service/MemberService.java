package com.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	public Member insert(Member member) {
		Member resultMember = memberRepository.save(member); //save=insert해주는 메소드
		return resultMember;
	}

	public Optional<Member> select(Long id) {
		/* 
		 * Optional<T>: NullpointerException을 방지하기 위해 사용
		 * 기본의 반환값 T에 Optional<T>를 Wrapping하며 null 대신 Optional안에 빈 타입 객체를 돌려줌
		 * =>Member member인 경우는 값이 안들어왔을 때 null이지만 Optional<Member> member로 하면 null대신 ""로 값이 비어있는 결과로 나오게
		 */		
		
		// findById(): Entitiy에서 @Id가 붙은 필드를 의미
		Optional<Member> member = memberRepository.findById(id);
		return member;
	}

	public List<Member> selectAll() {
		// findAll(): List 자료형을 갖는다
		return memberRepository.findAll();
	}

	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

	public Member update(Member member) { 
		// update 메소드가 따로 존재하지 않고 새로 insert한다는 개념으로 insert와 같이 save 메소드를 사용
		// @Id필드의 값이 DB에 들어있으면 업데이트, 없으면 insert해준다
		return memberRepository.save(member);
	}

}
