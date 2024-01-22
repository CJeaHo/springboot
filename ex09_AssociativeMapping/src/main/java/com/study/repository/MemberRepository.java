package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{ // <entity객체 클래스, @Id가 걸려있는 변수의 자료형>
	
}
