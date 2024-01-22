package com.study.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	public MemberRepository memberRepository;

	public Boolean idCheck(String id) {
		return memberRepository.existsById(id); // jpa에서 제공해주는 메소드로 id 존재 여부로 true, false 반환
	}

	public Member register(Member member) {
		return memberRepository.save(member);
	}

	public Member login (Member member) {
		Optional<Member> loginUser = memberRepository.findById(member.getId());
		if(loginUser.isPresent()) { // id가 들어있으면
			return loginUser.get(); // 벗겨서 return하므로 Optional<Member>가 아닌 Member자료형으로 반환 
									// 만약 안벗겼다면 Optional<Member>의 자료형으로 반환
		} else {
			return null;
		}
	}
}
