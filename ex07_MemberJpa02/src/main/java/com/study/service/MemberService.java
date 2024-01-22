package com.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;


@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	public void insert() {
		
		Member member;
		member = new Member("test1@test", "일순신");
		memberRepository.save(member);
		member = new Member("test2@test", "이범신");
		memberRepository.save(member);
		member = new Member("test3@test", "삼순신");
		memberRepository.save(member);
		member = new Member("test4@test", "사순신");
		memberRepository.save(member);
		member = new Member("test5@test", "오순신");
		memberRepository.save(member);
		member = new Member("test6@test", "육순신");
		memberRepository.save(member);
	}

	public List<Member> select() {
		return memberRepository.findAll();
	}

	public Optional<Member> selectById(Long id) {
		return memberRepository.findById(id);
	}

	public Optional<Member> selectByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	public Optional<Member> selectByName(String name) {
		return memberRepository.findByName(name);
	}
	
	public List<Member> selectByNameLike(String name) {
	    return memberRepository.findByNameLike(name);
	}

	public List<Member> selectByNameLikeDesc(String name) {
		return memberRepository.findByNameLikeOrderByNameDesc(name); // 메소드에 OrderBy필드명을 붙이면 차순을 설정할 수 있나..?
	}

	public List<Member> selectByNameLikeSort(String name, Sort sort) {
		return memberRepository.findByNameLike(name, sort);
	}


	
}
